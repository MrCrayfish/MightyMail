package com.mrcrayfish.mightymail.mail;

import net.minecraft.server.players.NameAndId;

import java.util.Optional;
import java.util.UUID;

/**
 * Author: MrCrayfish
 */
public interface IMailbox
{
    UUID getId();

    Optional<NameAndId> getOwner();

    Optional<String> getCustomName();
}
