package com.mrcrayfish.mightymail.client;

import com.mojang.authlib.GameProfile;
import com.mrcrayfish.mightymail.mail.IMailbox;
import net.minecraft.core.UUIDUtil;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.Utf8String;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.server.players.NameAndId;

import java.util.Optional;
import java.util.UUID;

/**
 * Author: MrCrayfish
 */
public record ClientMailbox(UUID id, Optional<NameAndId> owner, Optional<String> customName) implements IMailbox
{
    public static final StreamCodec<FriendlyByteBuf, NameAndId> NAME_AND_ID = StreamCodec.of((buf, nameAndId) -> {
        buf.writeUUID(nameAndId.id());
        buf.writeUtf(nameAndId.name());
    }, buf -> {
        UUID id = buf.readUUID();
        String name = buf.readUtf();
        return new NameAndId(id, name);
    });

    public static final StreamCodec<RegistryFriendlyByteBuf, IMailbox> STREAM_CODEC = StreamCodec.composite(
        UUIDUtil.STREAM_CODEC,
        IMailbox::getId,
        ByteBufCodecs.optional(NAME_AND_ID),
        IMailbox::getOwner,
        ByteBufCodecs.optional(ByteBufCodecs.stringUtf8(256)),
        IMailbox::getCustomName,
        ClientMailbox::new
    );

    @Override
    public UUID getId()
    {
        return this.id;
    }

    @Override
    public Optional<NameAndId> getOwner()
    {
        return this.owner;
    }

    @Override
    public Optional<String> getCustomName()
    {
        return this.customName;
    }
}
