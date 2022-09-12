package music_advisor.view;

public class View {
    int objectOnPage;
    int totalNumberOfPage;
    int numberOfCurrentPage;
    int indexOfFirstObjectOnPage;
    int indexOfLastObjectOnPage;
    String[] listToDisplay;

    public View(int entriesPerPage) {
        objectOnPage = entriesPerPage;
    }

    public void displayObjectOnPage(String data) {
        listToDisplay = data.split("\n\n");

        indexOfFirstObjectOnPage = 0;
        indexOfLastObjectOnPage = 0;

        if (listToDisplay.length % objectOnPage > 0) {
            totalNumberOfPage = listToDisplay.length / objectOnPage + 1;
        } else {
            totalNumberOfPage = listToDisplay.length / objectOnPage;
        }

        numberOfCurrentPage = 1;

        for (int i = 0; i < objectOnPage; i++) {
            System.out.println(listToDisplay[i] + "\n");
        }

        System.out.printf("---PAGE %d OF %d---\n", numberOfCurrentPage, totalNumberOfPage);

    }

    public void nextPage() {
        if (numberOfCurrentPage + 1 < totalNumberOfPage) {
            indexOfFirstObjectOnPage = objectOnPage * numberOfCurrentPage;
            indexOfLastObjectOnPage = indexOfFirstObjectOnPage + objectOnPage;
        } else if (numberOfCurrentPage + 1 == totalNumberOfPage) {
            indexOfFirstObjectOnPage = objectOnPage * numberOfCurrentPage;
            if (listToDisplay.length % objectOnPage > 0) {
                indexOfLastObjectOnPage = indexOfFirstObjectOnPage + listToDisplay.length % objectOnPage;
            } else {
                indexOfLastObjectOnPage = indexOfFirstObjectOnPage + objectOnPage;
            }
        } else {
            System.out.println("No more pages");
            return;
        }

        for (int i = indexOfFirstObjectOnPage; i < indexOfLastObjectOnPage; i++) {
            System.out.println(listToDisplay[i] + "\n");
        }
        numberOfCurrentPage++;

        System.out.printf("---PAGE %d OF %d---\n", numberOfCurrentPage, totalNumberOfPage);
    }

    public void previousPage() {
        numberOfCurrentPage--;
        if (numberOfCurrentPage > 0) {
            indexOfFirstObjectOnPage = indexOfFirstObjectOnPage - objectOnPage;
            indexOfLastObjectOnPage = indexOfFirstObjectOnPage + objectOnPage;
            for (int i = indexOfFirstObjectOnPage; i < indexOfLastObjectOnPage; i++) {
                System.out.println(listToDisplay[i] + "\n");
            }

            System.out.printf("---PAGE %d OF %d---\n", numberOfCurrentPage, totalNumberOfPage);
        } else {
            numberOfCurrentPage++;
            System.out.println("No more pages");
        }
    }
}
