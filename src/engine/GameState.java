// GameState: player progress (name, current node, visited nodes); only GameEngine may change it
//
// GameState(mutable progress)
// - String playerName
// - String currentNodeId
// - List<String> history
// + GameState(String playerName, String startNodeId)
// + String getPlayerName()
// + String getCurrentNodeId()
// + List<String> getHistory()
// + void moveTo(String nodeId)// package-private; only the engine calls it
