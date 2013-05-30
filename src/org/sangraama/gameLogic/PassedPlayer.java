package org.sangraama.gameLogic;

import java.util.HashMap;
import java.util.Map;

import org.sangraama.asserts.Player;
import org.sangraama.controller.WebSocketConnection;

public enum PassedPlayer {
    INSTANCE;

    private String TAG = "PassedPlayer :";
    
    private GameEngine engine = null;
    private Map<Long, Player> passdePlayers = null;

    private PassedPlayer() {
        this.passdePlayers = new HashMap<Long, Player>();
        this.engine = GameEngine.INSTANCE;
    }

    public void addPassedPlayer(Player p) {
        this.passdePlayers.put(p.getUserID(), p);
        System.out.println(TAG + "Added new passed player");
    }

    public void redirectPassPlayerConnection(long userID, WebSocketConnection con) {
        if (!passdePlayers.isEmpty()) {
            Player p = this.passdePlayers.get(userID);
            if (p != null) {
                p.setConnection(con);
                this.engine.addToPlayerQueue(p);
                this.passdePlayers.remove(userID);
                System.out.println(TAG + "Added passed player to GameEngine queue");
            }
        }
    }

}
