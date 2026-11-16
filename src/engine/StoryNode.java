// StoryNode: one scene (ID, description, choices); a node with no choices is an ending
//
// StoryNode
// - String id
// - String description
// - List<Choice> choices
// + String getId()
// + String getDescription()
// + List<Choice> getChoices()// returns an unmodifiable list
// + boolean isEnding()// choices.isEmpty()
