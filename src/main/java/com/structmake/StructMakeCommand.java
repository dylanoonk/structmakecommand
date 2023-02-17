package com.structmake;

import com.google.gson.JsonObject;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.state.BlockState;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import com.mojang.logging.LogUtils;

public class StructMakeCommand {

    private static final Logger LOGGER = LogUtils.getLogger();

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        LiteralArgumentBuilder<CommandSourceStack> command = Commands.literal("structmake")
            .then(Commands.argument("x1", IntegerArgumentType.integer())
                .then(Commands.argument("y1", IntegerArgumentType.integer())
                    .then(Commands.argument("z1", IntegerArgumentType.integer())
                        .then(Commands.argument("x2", IntegerArgumentType.integer())
                            .then(Commands.argument("y2", IntegerArgumentType.integer())
                                .then(Commands.argument("z2", IntegerArgumentType.integer())
                                    .executes(context -> {
                                        saveStructureDataToDisk(IntegerArgumentType.getInteger(context, "x1"), IntegerArgumentType.getInteger(context, "y1"), IntegerArgumentType.getInteger(context, "z1"), IntegerArgumentType.getInteger(context, "x2"), IntegerArgumentType.getInteger(context, "y2"), IntegerArgumentType.getInteger(context, "z2"), context.getSource());
                                        return 1;
                                        
                                    })
                                )
                            )
                        )
                    )
                )
            );

        dispatcher.register(command);
        
    }

    public static void saveStructureDataToDisk(int x1, int y1, int z1, int x2, int y2, int z2, CommandSourceStack source) {
        int minX = Math.min(x1, x2);
        int minY = Math.min(y1, y2);
        int minZ = Math.min(z1, z2);
        int maxX = Math.max(x1, x2);
        int maxY = Math.max(y1, y2);
        int maxZ = Math.max(z1, z2);


        
        String filename = "block_data_" + LocalDateTime.now().toString() + ".json";
        String illegalChars = "[\\\\/:*?\"<>|]";
        filename = filename.replaceAll(illegalChars, "_");
        File outputFile = new File("C:/Users/" + System.getProperty("user.name") + "/OneDrive/Desktop/" + filename);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            for (int x = minX; x <= maxX; x++) {
                for (int y = minY; y <= maxY; y++) {
                    for (int z = minZ; z <= maxZ; z++) {
                        BlockPos pos = new BlockPos(x, y, z);
                        BlockState state = source.getLevel().getBlockState(pos);
                        JsonObject blockData = new JsonObject();
                        blockData.addProperty("x", x);
                        blockData.addProperty("y", y);
                        blockData.addProperty("z", z);
                        blockData.addProperty("block", Registry.BLOCK.getKey(state.getBlock()).toString());
                        blockData.addProperty("state", state.toString());
                        writer.write(blockData.toString());
                        writer.newLine();
                    }
                }
            }
            source.sendSuccess(Component.nullToEmpty("<StructMake> Saved to " + outputFile.getAbsolutePath()), false);
        } catch (Exception e) {
            source.sendSuccess(Component.nullToEmpty("<StructMake> Failed to save " + filename), false);
            source.sendSuccess(Component.nullToEmpty("<StructMake> " + e.getLocalizedMessage()), false);
            LOGGER.info(e.getLocalizedMessage());
            LOGGER.info(e.getStackTrace().toString());
            LOGGER.info("failed to save to " + outputFile.getAbsolutePath());

        }

    }
    

    
}
