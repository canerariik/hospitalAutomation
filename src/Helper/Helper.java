package Helper;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Helper {

	public static void optionPanelChangeButtonText() {
		UIManager.put("OptionPane.cancelButtonText", "İptal");
		UIManager.put("OptionPane.noButtonText", "Hayır");
		UIManager.put("OptionPane.okButtonText", "Tamam");
		UIManager.put("OptionPane.yesButtonText", "Evet");
	}

	public static void showMsg(String str) {
		String msg;
		optionPanelChangeButtonText();

		switch (str) {
		case "fill":
			msg = "Lütfen tüm alanları doldurunuz.";
			break;
		case "success":
			msg = "İşlem başarılı.";
			break;
		case "error":
			msg = "İşlem gerçekleştirilemedi.";
			break;
		default:
			msg = str;
		}
		JOptionPane.showMessageDialog(null, msg, "Mesaj", JOptionPane.INFORMATION_MESSAGE);
	}

	public static boolean confirm(String str) {
		String msg;
		optionPanelChangeButtonText();

		switch (str) {
		case "sure":
			msg = "Bu işlemi gerçekleştirmek istiyor musun?";
			break;
		default:
			msg = str;
			break;
		}

		int res = JOptionPane.showConfirmDialog(null, msg, "Dikkat!", JOptionPane.YES_NO_OPTION);

		if (res == 0) {
			return true;
		} else {
			return false;
		}
	}

}
