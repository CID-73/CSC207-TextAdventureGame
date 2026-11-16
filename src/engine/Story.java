// Story: the whole story (title, start node, all nodes); validates every link on creation
//
// Story
// - String title
// - String startNodeId
// - Map<String, StoryNode> nodes
// + Story(String title, String startNodeId, Map<String, StoryNode> nodes)
// + String getTitle()
// + StoryNode getStartNode()
// + Optional<StoryNode> findNode(String nodeId)
// + StoryNode getNode(String nodeId)
// - void validate()// start node exists, all choice targets exist
