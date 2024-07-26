package com.zooi.immortalpets.mixin;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.TameableEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AnimalEntity.class)
public class TameableEntityMixin {
    @Inject(at = @At("HEAD"), method = "damage", cancellable = true)
    public void preventDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        var animal = (AnimalEntity) (Object) this;

        if (source.getType().equals(animal.getWorld().getDamageSources().genericKill().getType()))
            return;

        if (animal instanceof TameableEntity pet && pet.isTamed()) {
            cir.setReturnValue(false);
            cir.cancel();
        }
    }
}
