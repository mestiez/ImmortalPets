package com.zooi.immortalpets.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Tameable;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.WolfEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(at = @At("HEAD"), method = "damage", cancellable = true)
    public void preventDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        var e = (LivingEntity) (Object) this;

        if (source.getType().equals(e.getWorld().getDamageSources().genericKill().getType()))
            return;

        if (e instanceof Tameable pet && pet.getOwnerUuid() != null) {
            cir.setReturnValue(false);
            cir.cancel();
        }
    }
}

