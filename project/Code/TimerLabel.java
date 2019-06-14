public class TimerLabel extends JLabel {
    // Add in your code for 'format' and 'remainingTime'.
    // Note that the first time that 'getText' is called, it's called from the constructor
    // if the superclass, so your own class is not fully initialized at this point.
    // Hence the 'if (format != null)' check

    public TimerLabel() {
        Timer timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        });
        timer.start();
    }

    public String getRemainingTime() {
        int hours = (int) ((this.remainingTime / 3600000) % 60);
        int minutes = (int) ((this.remainingTime / 60000) % 60);
        int seconds = (int) (((this.remainingTime) / 1000) % 60);

        return (format.format(hours) + ":" + format.format(minutes) + ":" + format.format(seconds));
    }

    @Override
    public String getText() {
        if (format != null) {
            return getRemainingTime();
        } else {
            return "";
        }
    }
    }