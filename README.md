# The Humble Dialog Box

This is an implementation of the Humble Dialog box as described in the article by Michael Feathers: https://martinfowler.com/articles/images/humble-dialog-box/TheHumbleDialogBox.pdf

The motivation in the article is the separation of a single GUI component to two parts:

- a "humble" object that holds all the subcomponents and has trivial logic.
- a "smart" object that holds an abstract representation of the component's state and the logic that coordinates the components.

The separation enables testing of the component without the complications of dealing with the event-driven mechanics of the GUI framework.

## The humble object

The humble object `ChainComposerDialog` has a reference to the smart object `ChainComposer`.

```java
public class ChainComposerDialog extends JDialog implements ChainComposerView {

    private ChainComposer composer;
    ...
```

Each component within `ChainComposerDialog` delegates its changes to the smart object via a simple action method.

```java
    private JButton addButton = new JButton("Add");
...
    addButton.addActionListener(e -> onAdd());
...
    private void onAdd() {
        composer.add();
    }

```

The rest of the methods in `ChainComposerDialog` humble object are simple setters.

```java
    public void setAddButtonEnabled(boolean b) {
        addButton.setEnabled(b);
    }
```

So, overall, the humble object is extremely simple. Listeners delegating actions to the smart object and dead simple setters for the subcomponents.

## The smart object

All the logic for the coordination of the subcomponents is moved inside the "smart" object `ChainComposer`.

The smart object `ChainComposer` also has a reference to the humble object via the `ChainComposerView` interface.

```java
public class ChainComposer {
    private ChainComposerView view;

```

The smart object holds also an abstract representation of the state in the humble object. It holds all the necessary data in order to render the view object.

```java
    private List<Filter> selectionList;
    private List<Filter> chainList;
```

The action methods of the smart object contain the logic that

- updates the state of the smart object and
- updates the components in the humble object:

```java
    public void add() {
        int i = selectedIndex;
        if (i >= 0 && i < selectionList.size()) {
            this.chainList.add(selectionList.get(i));
            view.setChainList(chainList);
        }
        // deselect the filter in the selection list
        selectedIndex = -1;
        view.setSelectedIndex(selectedIndex);
    }
```

## Testing the "smart" object

In order to test the smart object without dealing with the complications of the GUI component, the view is mocked. An interface is created that holds all the necessary
setter methods for updating the sub components:

```java
public interface ChainComposerView {
    void setSelectionList(List<Filter> filters);
    ...

    void setAddButtonEnabled(boolean b);

    ...

}
```

The mock implementation of the view provides trivial implementations for the setters.
It also provides getters being used by the test assertions.

```java
public class MockChainComposerView implements ChainComposerView {

    private List<Filter> selectionList;
    ...
    @Override
    public void setSelectionList(List<Filter> filters) {
        this.selectionList = filters;
    }

    public List<Filter> selectionList() {
        return selectionList;
    }
```

All the tests should only interact with the smart object `composer`. There should be no
update interactions with the view! Only get interactions.
Updating the view should be the sole responsibility of the smart object.
