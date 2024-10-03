package com.dotnomi.stranded.networking.packet;

import com.dotnomi.stranded.data.StrandedWorldState;
import com.dotnomi.stranded.data.StrandedWorldStateManager;
import com.dotnomi.stranded.Stranded;
import com.dotnomi.stranded.service.ExampleDataService;
import com.dotnomi.stranded.util.IEntityDataSaver;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public record ExampleC2SPacket(int number) implements CustomPayload {
    public static final CustomPayload.Id<ExampleC2SPacket> IDENTIFIER =
        new CustomPayload.Id<>(Identifier.of(Stranded.MOD_ID, "example_c2s_packet"));

    public static final PacketCodec<RegistryByteBuf, ExampleC2SPacket> CODEC =
        PacketCodec.tuple(
            PacketCodecs.INTEGER,
            ExampleC2SPacket::number,
            ExampleC2SPacket::new
        );

    @Override
    public Id<? extends CustomPayload> getId() {
        return IDENTIFIER;
    }

    public void sendPacket() {
        ClientPlayNetworking.send(this);
    }

    public void handlePacket(ServerPlayNetworking.Context context) {
        IEntityDataSaver dataEntity = (IEntityDataSaver) context.player();
        ExampleDataService.addNumber(dataEntity, number);

        StrandedWorldState strandedWorldState =
            StrandedWorldStateManager.getStrandedWorldState(context.player().getServerWorld());

        strandedWorldState.addDummyData(number);

        context.player().sendMessage(Text.translatable("message.stranded.server_received").append(" Number: " + ExampleDataService.getNumber(dataEntity)), true);
    }
}
