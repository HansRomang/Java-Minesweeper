import javax.swing.*; 
import javax.swing.Timer;
 
public class GUI extends JFrame {

    public Timer timer = null;

    public GUI() {
        timer = new Timer (500, new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if (timerGetsToZero) {
                    ((Timer)e.getSource()).stop();
                } else {
                    timeLabel.setText(getRemainingTime());
                }
            }
        });
    }

    private void startButtonActionPerformed(ActionEvent e) {
        timer.start();
    }
}