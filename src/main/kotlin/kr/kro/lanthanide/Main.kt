package kr.kro.lanthanide

import kr.kro.lanthanide.command.GiveCommand
import kr.kro.lanthanide.command.StopCommand
import net.minestom.server.MinecraftServer
import net.minestom.server.command.CommandManager
import net.minestom.server.coordinate.Pos
import net.minestom.server.entity.Player
import net.minestom.server.event.GlobalEventHandler
import net.minestom.server.event.player.PlayerLoginEvent
import net.minestom.server.instance.InstanceContainer
import net.minestom.server.instance.InstanceManager
import net.minestom.server.instance.block.Block

fun main() {
    // get var
    val minecraftServer: MinecraftServer = MinecraftServer.init()
    val instanceManager: InstanceManager = MinecraftServer.getInstanceManager()
    val commandManager: CommandManager = MinecraftServer.getCommandManager()
    val globalEventHandler: GlobalEventHandler = MinecraftServer.getGlobalEventHandler()

    commandManager.register(StopCommand())
    commandManager.register(GiveCommand())

    val instanceContainer: InstanceContainer = instanceManager.createInstanceContainer()

    instanceContainer.setGenerator { unit -> unit.modifier().fillHeight(0, 40, Block.GRASS_BLOCK) }


    globalEventHandler.addListener(PlayerLoginEvent::class.java) { event ->
        val player: Player = event.player
        event.setSpawningInstance(instanceContainer)
        player.respawnPoint = Pos(0.0, 42.0, 0.0)
    }

    minecraftServer.start("0.0.0.0", 25565)
}
