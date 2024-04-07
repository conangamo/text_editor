package MVC;

public class Main {
    public static void main(String[] args) {
        TextModel model = new TextModel();
        TextView view = new TextView();
        TextController controller = new TextController(model, view);

        view.setVisible(true);
    }
}

