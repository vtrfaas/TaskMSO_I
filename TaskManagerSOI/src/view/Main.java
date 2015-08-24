package view;

import javax.swing.JOptionPane;

import controller.ProcessosController;

public class Main {

	public static void main(String[] args) {
		ProcessosController processo;
		byte op = 0;
		while(op != 3){
			op = Byte.parseByte(JOptionPane.showInputDialog("MENU:\n 1 - LISTAR PROCESSOS\n 2 - MATAR PROCESSO\n 3 - SAIR"));
			switch(op){
				case 1:
					processo = new ProcessosController();
					String osName = processo.getOsName();
					System.out.println(processo.lerProcesso(osName));
					break;
				case 2:
					processo = new ProcessosController();
					String nomeSO = processo.getOsName();
					String id = JOptionPane.showInputDialog("Digite o PID ou nome do processo que deseja finalizar: ");
					System.out.println(processo.mataProcesso(id, nomeSO));
					break;
				case 3:
					System.exit(0);
				default:
					JOptionPane.showMessageDialog(null, "Invalid Option", "System Message", JOptionPane.ERROR_MESSAGE);
					break;
			}
		}

	}

}
