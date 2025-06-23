package com.zooi.immortalpets.mixin;

import net.minecraft.entity.Tameable;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.AnimalEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AnimalEntity.class)
public class AnimalMixin {
    @Inject(at = @At("HEAD"), method = "damage", cancellable = true)
    public void preventDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        var animal = (AnimalEntity) (Object) this;

        if (source.getType().equals(animal.getWorld().getDamageSources().genericKill().getType()))
            return;

        if (animal instanceof Tameable pet && pet.getOwnerUuid() != null) {
            cir.setReturnValue(false);
            cir.cancel();
        }
    }
}

