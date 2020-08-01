/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evm;

/**
 *
 * @author robinhood
 */
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class Error {
    admin a = new admin();
  public Error(String er,JFrame f){
      a.dispose();
      f.setVisible(true);
      err(er);
  }
  public void welcome(){
      
  }
  public Error(String er){
      err(er);
      a.setVisible(true);
  }

  public void err(String err) {
    final JPanel panel = new JPanel();
    JOptionPane.showMessageDialog(panel, err, "Error", JOptionPane.ERROR_MESSAGE);

  }

    static class Welcome {
        admin a = new admin();
        public Welcome() {
            final JPanel panel = new JPanel();
            JOptionPane.showMessageDialog(panel, "Are You Ready To Vote", "Welcome", JOptionPane.PLAIN_MESSAGE);
            a.setVisible(true);
        }
    }
}