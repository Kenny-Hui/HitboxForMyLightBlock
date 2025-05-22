package com.lx862.hitboxforlight.client.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.lx862.hitboxforlight.client.HitboxForMyLightBlockClient;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LightBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LightBlock.class)
public class LightBlockMixin extends Block {
    public LightBlockMixin(Block.Properties properties) {
        super(properties);
    }

    @WrapOperation(method = "getShape", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/phys/shapes/CollisionContext;isHoldingItem(Lnet/minecraft/world/item/Item;)Z"))
    public boolean changeOutlineShape(CollisionContext instance, Item item, Operation<Boolean> original) {
        boolean ourResult = HitboxForMyLightBlockClient.getConfig().shouldShowHitbox(item);
        return ourResult || original.call(instance, item);
    }

    // Explicitly override
    @Override
    protected VoxelShape getCollisionShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return Shapes.empty();
    }
}
