package io.github.sakuraryoko.corelib.commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import io.github.sakuraryoko.corelib.network.test.TestSuite;
import io.github.sakuraryoko.corelib.util.CoreLog;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class testCommand {
    public static void register()
    {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(
                literal("testsuite")
                        .executes(ctx -> runTestServer(ctx.getSource()))
                        .then(argument("player", EntityArgumentType.player())
                                //.requires(Permissions.require("afkplus.afk", 0))
                                .executes((ctx) -> runTestPlayer(ctx.getSource(), EntityArgumentType.getPlayer(ctx,"player"), "Random player message"))
                                .then(argument("message", StringArgumentType.greedyString())
                                    .executes((ctx) -> runTestPlayer(ctx.getSource(), EntityArgumentType.getPlayer(ctx,"player"), StringArgumentType.getString(ctx, "message")))
                        )
        )));
    }
    private static int runTestServer(ServerCommandSource src) throws CommandSyntaxException {
        String user = src.getPlayerOrThrow().getName().getLiteralString();
        // Run C2S test
        TestSuite.testC2S("Random server message");
        CoreLog.debug("runTestServer(): --> Executed!");
        return 1;
    }
    private static int runTestPlayer(ServerCommandSource src, ServerPlayerEntity target, String message) throws CommandSyntaxException {
        String user = src.getPlayerOrThrow().getName().getLiteralString();
        if (target != null)
        {
            // Run C2S test
            TestSuite.testS2C(target, message);
        }
        else {
            // Run C2S test
            TestSuite.testC2S(message);
        }
        CoreLog.debug("runTestPlayer(): --> Executed!");
        return 1;
    }

}
