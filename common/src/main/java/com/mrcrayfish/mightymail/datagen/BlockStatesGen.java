package com.mrcrayfish.mightymail.datagen;

import com.mrcrayfish.framework.api.datagen.FrameworkGenerator;
import com.mrcrayfish.mightymail.block.MailboxBlock;
import com.mrcrayfish.mightymail.block.PostBoxBlock;
import com.mrcrayfish.mightymail.core.ModBlocks;
import com.mrcrayfish.mightymail.util.Utils;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.BlockModelDefinitionGenerator;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.renderer.block.model.Variant;
import net.minecraft.client.renderer.item.ClientItem;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.WoodType;

import java.util.Map;

import static net.minecraft.client.data.models.BlockModelGenerators.*;

@SuppressWarnings("UnstableApiUsage")
public class BlockStatesGen extends FrameworkGenerator
{
    public BlockStatesGen(Map<Block, BlockModelDefinitionGenerator> generators, Map<Item, ClientItem> items, Map<ResourceLocation, ModelInstance> models)
    {
        super(generators, items, models);
    }

    @Override
    public void generate()
    {
        this.mailbox(ModBlocks.MAIL_BOX_OAK.get(), WoodType.OAK);
        this.mailbox(ModBlocks.MAIL_BOX_SPRUCE.get(), WoodType.SPRUCE);
        this.mailbox(ModBlocks.MAIL_BOX_BIRCH.get(), WoodType.BIRCH);
        this.mailbox(ModBlocks.MAIL_BOX_JUNGLE.get(), WoodType.JUNGLE);
        this.mailbox(ModBlocks.MAIL_BOX_ACACIA.get(), WoodType.ACACIA);
        this.mailbox(ModBlocks.MAIL_BOX_DARK_OAK.get(), WoodType.DARK_OAK);
        this.mailbox(ModBlocks.MAIL_BOX_MANGROVE.get(), WoodType.MANGROVE);
        this.mailbox(ModBlocks.MAIL_BOX_CHERRY.get(), WoodType.CHERRY);
        this.mailbox(ModBlocks.MAIL_BOX_CRIMSON.get(), WoodType.CRIMSON);
        this.mailbox(ModBlocks.MAIL_BOX_WARPED.get(), WoodType.WARPED);
        this.mailbox(ModBlocks.MAIL_BOX_PALE_OAK.get(), WoodType.PALE_OAK);
        this.postBox(ModBlocks.POST_BOX.get());
    }

    private ResourceLocation woodParticle(WoodType type)
    {
        return ResourceLocation.withDefaultNamespace("block/" + type.name() + "_planks");
    }

    private ResourceLocation blockTexture(Block block)
    {
        ResourceLocation name = BuiltInRegistries.BLOCK.getKey(block);
        return ResourceLocation.fromNamespaceAndPath(name.getNamespace(), "block/" + name.getPath());
    }

    private void registerItemWithModelFromMultiVariant(Block block, MultiVariant variant)
    {
        ResourceLocation location = variant.variants().unwrap().getFirst().value().modelLocation();
        this.items.put(block.asItem(), this.createClientItem(ItemModelUtils.plainModel(location)));
    }
    
    private Variant plainModel(ResourceLocation location)
    {
        return new Variant(location);
    }
    
    private MultiVariant variant(Variant variant)
    {
        return new MultiVariant(WeightedList.of(variant));
    }
    
    private MultiVariant plainVariant(ResourceLocation location)
    {
        return this.variant(this.plainModel(location));
    }

    private void mailbox(MailboxBlock block, WoodType type)
    {
        TextureMapping textures = new TextureMapping()
            .put(TextureSlot.PARTICLE, this.woodParticle(type))
            .put(TextureSlot.TEXTURE, this.blockTexture(block));
        MultiVariant mailboxVariant = this.plainVariant(ModelDefinitions.MAIL_BOX.create(block, textures, this.models::put));
        MultiVariant mailboxUncheckedVariant = this.plainVariant(ModelDefinitions.MAIL_BOX_UNCHECKED.create(block, textures, this.models::put));
        this.registerItemWithModelFromMultiVariant(block, mailboxVariant);
        this.generators.put(block, MultiVariantGenerator.dispatch(block)
            .with(PropertyDispatch.initial(MailboxBlock.DIRECTION, MailboxBlock.ENABLED)
                .select(Direction.NORTH, false, mailboxVariant)
                .select(Direction.EAST, false, mailboxVariant.with(Y_ROT_90))
                .select(Direction.SOUTH, false, mailboxVariant.with(Y_ROT_180))
                .select(Direction.WEST, false, mailboxVariant.with(Y_ROT_270))
                .select(Direction.NORTH, true, mailboxUncheckedVariant)
                .select(Direction.EAST, true, mailboxUncheckedVariant.with(Y_ROT_90))
                .select(Direction.SOUTH, true, mailboxUncheckedVariant.with(Y_ROT_180))
                .select(Direction.WEST, true, mailboxUncheckedVariant.with(Y_ROT_270))));
    }

    private void postBox(PostBoxBlock block)
    {
        MultiVariant postboxVariant = this.plainVariant(ModelLocationUtils.getModelLocation(block));
        this.registerItemWithModelFromMultiVariant(block, postboxVariant);
        this.generators.put(block, MultiVariantGenerator.dispatch(block)
            .with(PropertyDispatch.initial(PostBoxBlock.DIRECTION)
                .select(Direction.NORTH, postboxVariant)
                .select(Direction.EAST, postboxVariant.with(Y_ROT_90))
                .select(Direction.SOUTH, postboxVariant.with(Y_ROT_180))
                .select(Direction.WEST, postboxVariant.with(Y_ROT_270))));
    }
}
