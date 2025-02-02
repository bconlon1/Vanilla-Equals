package com.bconlon.vanillaequals.client.renderer.entity.model;

import net.minecraft.client.model.CowModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.world.entity.animal.Cow;

public class DetailedCowModel extends CowModel<Cow> {
    public DetailedCowModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshDefinition = new MeshDefinition();
        PartDefinition partDefinition = meshDefinition.getRoot();
        partDefinition.addOrReplaceChild(
                "head",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-4.0F, -4.0F, -6.0F, 8.0F, 8.0F, 6.0F)
                        .texOffs(22, 0)
                        .addBox("right_horn", -5.0F, -5.0F, -4.0F, 1.0F, 3.0F, 1.0F)
                        .texOffs(26, 0)
                        .addBox("left_horn", 4.0F, -5.0F, -4.0F, 1.0F, 3.0F, 1.0F),
                PartPose.offset(0.0F, 4.0F, -8.0F)
        );
        partDefinition.addOrReplaceChild(
                "body",
                CubeListBuilder.create()
                        .texOffs(18, 4)
                        .addBox(-6.0F, -10.0F, -7.0F, 12.0F, 18.0F, 10.0F)
                        .texOffs(52, 0)
                        .addBox(-2.0F, 2.0F, -8.0F, 4.0F, 6.0F, 1.0F),
                PartPose.offsetAndRotation(0.0F, 5.0F, 2.0F, (float) (Math.PI / 2), 0.0F, 0.0F)
        );
        partDefinition.addOrReplaceChild("right_hind_leg", CubeListBuilder.create().texOffs(0, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F), PartPose.offset(-4.0F, 12.0F, 7.0F));
        partDefinition.addOrReplaceChild("left_hind_leg", CubeListBuilder.create().texOffs(16, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F), PartPose.offset(4.0F, 12.0F, 7.0F));
        partDefinition.addOrReplaceChild("right_front_leg", CubeListBuilder.create().texOffs(0, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F), PartPose.offset(-4.0F, 12.0F, -6.0F));
        partDefinition.addOrReplaceChild("left_front_leg", CubeListBuilder.create().texOffs(16, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F), PartPose.offset(4.0F, 12.0F, -6.0F));
        return LayerDefinition.create(meshDefinition, 64, 64);
    }

    public ModelPart getHead() {
        return this.head;
    }
}
