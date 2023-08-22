package com.mrcrayfish.mightymail;

import com.mrcrayfish.framework.api.event.TickEvents;
import com.mrcrayfish.mightymail.core.ModItems;
import com.mrcrayfish.mightymail.item.PackageItem;
import com.mrcrayfish.mightymail.mail.DeliveryService;
import com.mrcrayfish.mightymail.network.Network;
import net.minecraft.core.Direction;
import net.minecraft.world.Containers;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.phys.Vec3;

/**
 * Author: MrCrayfish
 */
public class Bootstrap
{
    public static void init()
    {
        Network.init();
        TickEvents.START_SERVER.register(server -> DeliveryService.get(server).ifPresent(DeliveryService::serverTick));
        DispenserBlock.registerBehavior(ModItems.PACKAGE::get, (source, stack) -> {
            Direction direction = source.getBlockState().getValue(DispenserBlock.FACING);
            Vec3 pos = Vec3.atCenterOf(source.getPos().relative(direction));
            PackageItem.getPackagedItems(stack).forEach(s -> {
                Containers.dropItemStack(source.getLevel(), pos.x, pos.y, pos.z, s);
            });
            return ItemStack.EMPTY;
        });
    }
}
