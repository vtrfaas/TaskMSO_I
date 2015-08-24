package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ProcessosController {
	public String lerProcesso(String osName){
		String tarefa = null;
		if(osName.contains("Windows"))
			tarefa = "tasklist.exe";
		else if(osName.contains("Linux"))
			tarefa = "ps -ef";
		StringBuffer buffer = new StringBuffer();
			try {
				Process processo = Runtime.getRuntime().exec(tarefa);
				InputStream fluxo = processo.getInputStream();
				InputStreamReader leFluxo = new InputStreamReader(fluxo);
				BufferedReader bufferLeitura = new BufferedReader(leFluxo);
				
				String linha = bufferLeitura.readLine();
				while(linha != null){
					buffer.append(linha);
					buffer.append("\n");
					linha = bufferLeitura.readLine();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			return buffer.toString();
	}
	
	public String mataProcesso(String id, String osName){
		StringBuffer buffer = new StringBuffer();
		if(osName.contains("Windows")){
			buffer.append("taskkill.exe ");
			int pid = 0;
			try{
				pid = Integer.parseInt(id);
				buffer.append("/PID ");
				buffer.append(id);
			}catch(NumberFormatException e){
				buffer.append("/IM ");
				buffer.append(id);
			}
		}else{
			if(osName.contains("Linux")){
				int pid = 0;
				try{
					pid = Integer.parseInt(id);
					buffer.append("kill -9 ");
					buffer.append(id);
				}catch(NumberFormatException e){
					buffer.append("killall -9 ");
					buffer.append(id);
				}
			}
		}
		try{
			Process processo = Runtime.getRuntime().exec(buffer.toString());
			return "Comando: " + buffer.toString() + " executado com sucesso!";
		}catch(IOException e){
			return e.getMessage();
		}
	}
	
	public String getOsName(){
		String osName = System.getProperty("os.name");
		return osName;
	}
}
