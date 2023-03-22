package kr.kro.lanthanide.command

import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.CommandExecutor
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.entity.Entity
import net.minestom.server.entity.Player

/**
 * give player item
 */
class GiveCommand: Command("give") {
    init {
        defaultExecutor =
            CommandExecutor { sender, _ ->
                sender.sendMessage("usage: /give player item count")
            }

        val playerArgument = ArgumentType.Entity("player")
        val itemArgument = ArgumentType.ItemStack("item")
        val countArgument = ArgumentType.Integer("count")

        playerArgument.setCallback { sender, exception ->
            val input: String = exception.input
            sender.sendMessage("$input is invalid!")
        }

        itemArgument.setCallback { sender, exception ->
            val input: String = exception.input
            sender.sendMessage("$input is invalid!")
        }

        countArgument.setCallback { sender, exception ->
            val input: String = exception.input
            sender.sendMessage("$input is invalid!")
        }

        addSyntax({ sender, context ->
            val entityList: List<Entity> = context.get(playerArgument).find(sender)
            val playerList: List<Player> = entityList.filterIsInstance<Player>()

            for (player in playerList) {
                player.inventory.addItemStack(context.get(itemArgument).withAmount(context.get(countArgument)))
            }
        }, playerArgument, itemArgument, countArgument)
    }
}