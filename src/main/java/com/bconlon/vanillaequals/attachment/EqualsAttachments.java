package com.bconlon.vanillaequals.attachment;

import com.bconlon.vanillaequals.VanillaEquals;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class EqualsAttachments {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENTS = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, VanillaEquals.MODID);

    public static final DeferredHolder<AttachmentType<?>, AttachmentType<MobVariantAttachment>> MOB_VARIANT = ATTACHMENTS.register("mob_variant", () -> AttachmentType.builder(MobVariantAttachment::new).serialize(MobVariantAttachment.CODEC).build());
    public static final DeferredHolder<AttachmentType<?>, AttachmentType<LivestockAttachment>> LIVESTOCK = ATTACHMENTS.register("livestock", () -> AttachmentType.builder(LivestockAttachment::new).serialize(LivestockAttachment.CODEC).build());
}
