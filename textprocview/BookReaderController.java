package textprocview;
import textproc.*;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Container;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class BookReaderController {
    public BookReaderController(GeneralWordCounter counter) {
        SwingUtilities.invokeLater(() -> createWindow(counter, "BookReader", 100, 300));
    }

    private void createWindow(GeneralWordCounter counter, String title, int width, int height) {
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container pane = frame.getContentPane();
        // pane är en behållarkomponent till vilken de övriga komponenterna (listvy, knappar etc.) ska läggas till.
        
        List<Map.Entry<String, Integer>> wordList = counter.getWordList();
        wordList.removeIf(e -> Character.isDigit(e.getKey().charAt(0))); //sorterar bort alla nummer i början av ordet
        SortedListModel<Map.Entry<String, Integer>> listModel = new SortedListModel<>(wordList);
        JList<Map.Entry<String, Integer>> listView = new JList<>(listModel);

        JPanel panel = new JPanel();
        
        JButton Alphabetic = new JButton("Alphabetic");
        Alphabetic.addActionListener(e -> {
            System.out.println("Alphabetic");
            listModel.sort((e1,e2) -> e1.getKey().compareTo(e2.getKey()));  //sorterar alfabetiskt
        });
            
        JButton Frequency = new JButton("Frequency");
        Frequency.addActionListener(e ->{
            System.out.println("Frequency");
            listModel.sort((e1,e2) -> e2.getValue().compareTo(e1.getValue())); //sorterar efter frekvens
        });

        JTextField SearchField = new JTextField(20);
        
        JButton SearchButton = new JButton("Search");
        SearchButton.addActionListener(e -> {
            String search = SearchField.getText().trim().toLowerCase(); //tar vad som är inskrivet från textrutan och formaterar om
            
            for(int x = 0; x < listModel.getSize(); x++){
                String Key = listModel.getElementAt(x).getKey(); //hämtar nyckeln ur listan för att sen jämföra
                if (Key.equals(search)){
                    listView.setSelectedIndex(x);
                    listView.ensureIndexIsVisible(x);
                    return;
                }
            }
            JOptionPane.showMessageDialog(null, "ordet '" + search + "' finns inte i boken");
        });
        
        SearchField.addActionListener(e -> SearchButton.doClick()); //aktiverar searchbutton vid enter
        
        panel.add(Alphabetic);
        panel.add(Frequency);
        panel.add(SearchField);
        panel.add(SearchButton);

        pane.add(panel, BorderLayout.SOUTH);

        JScrollPane scrollPane = new JScrollPane(listView);

        pane.add(scrollPane);
        
        
        frame.pack();
        frame.setVisible(true);
    }
}
