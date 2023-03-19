package kr.kro.lanthanide.command

import net.minestom.server.MinecraftServer
import net.minestom.server.command.CommandSender
import net.minestom.server.command.ConsoleSender
import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.CommandContext
import net.minestom.server.command.builder.CommandExecutor

class StopCommand : Command("stop") {
    init {
        defaultExecutor =
            CommandExecutor { sender: CommandSender, _: CommandContext? ->
                if (sender.hasPermission("lanthanide.commands.stop") || sender is ConsoleSender) {
                    MinecraftServer.LOGGER.info("Turning off the server...")
                    MinecraftServer.stopCleanly()
                } else {
                    sender.sendMessage("You don't have permission to stop server!")
                }
            }

    }
}