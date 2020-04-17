package sample;

public class SearchWindowTableGroup extends MainWindowTableGroup {
    private Controller controller;

    public SearchWindowTableGroup(Controller controller) {
        super(controller);
        this.controller = controller;
    }

    @Override
    public void updateCurrentPage() {
        setCurrentPage(controller.updateSearchWindowTable(getPageNumber(), getRecordsOnPageCount()));
    }
}
