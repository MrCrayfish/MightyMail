package com.mrcrayfish.mightymail.client;

import com.mojang.authlib.GameProfile;
import com.mrcrayfish.mightymail.mail.IMailbox;
import net.minecraft.core.UUIDUtil;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.Utf8String;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

import java.util.Optional;
import java.util.UUID;

/**
 * Author: MrCrayfish
 */
public record ClientMailbox(UUID id, Optional<GameProfile> owner, Optional<String> customName) implements IMailbox
{
    public static final StreamCodec<FriendlyByteBuf, GameProfile> GAME_PROFILE_NO_PROPERTIES = StreamCodec.of((buf, profile) -> {
        UUIDUtil.STREAM_CODEC.encode(buf, profile.getId());
        Utf8String.write(buf, profile.getName(), 16);
    }, buf -> {
        UUID id = UUIDUtil.STREAM_CODEC.decode(buf);
        String name = Utf8String.read(buf, 16);
        return new GameProfile(id, name);
    });

    public static final StreamCodec<RegistryFriendlyByteBuf, IMailbox> STREAM_CODEC = StreamCodec.composite(
        UUIDUtil.STREAM_CODEC,
        IMailbox::getId,
        ByteBufCodecs.optional(GAME_PROFILE_NO_PROPERTIES),
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
    public Optional<GameProfile> getOwner()
    {
        return this.owner;
    }

    @Override
    public Optional<String> getCustomName()
    {
        return this.customName;
    }
}
