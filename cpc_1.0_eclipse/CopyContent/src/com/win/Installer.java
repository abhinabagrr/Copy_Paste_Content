package com.win;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Paths;

/**
 * Installs the cpc
 * @author abhibasu
 *
 */
public class Installer {

	private static final String CALLCPX_BAT = "callcpx.bat";
	private static final String CPX_BAT = "cpx.bat";
	private static final String CP_REG = "cp.reg";
	private static final String PST_REG = "pst.reg";

	public static void main(String[] args) {

		if (args.length < 1) {
			System.out.println("No param passed...");
			return;
		}
		String param = args[0];
		if ("-c".equalsIgnoreCase(param.trim())) {
			createReg();
		} else if ("-r".equalsIgnoreCase(param.trim())) {
			removeReg();
		}

	}

	private static void removeReg() {
		System.out.println("Removing temp files...");
		File f = new File(CP_REG);
		f.deleteOnExit();

		f = new File(PST_REG);
		f.deleteOnExit();
		
		f = new File(CPX_BAT);
		f.deleteOnExit();
		
		f = new File(CALLCPX_BAT);
		f.deleteOnExit();
	}

	private static void createReg() {
		System.out.println("Creating registry...");
		{
			StringBuilder builder = new StringBuilder();
			builder.append("Windows Registry Editor Version 5.00\n\n");
			builder.append("[HKEY_CLASSES_ROOT\\*\\shell\\Copy Content]\n\n");
			builder.append("[HKEY_CLASSES_ROOT\\*\\shell\\Copy Content\\command]\n");
			String curD = Paths.get("").toAbsolutePath().toString();
			curD = curD.replace("\\", "\\\\");
			builder.append("@=\"" + curD + "\\\\bin\\\\copy \\\"" + curD + "\\\\bin\\\" \\\"%1\\\"\"");
			PrintWriter out = null;
			try {
				out = new PrintWriter(CP_REG);
				System.out.println(builder.toString());
				out.print(builder.toString());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				out.close();
			}
		}

		{
			StringBuilder builder = new StringBuilder();
			builder.append("Windows Registry Editor Version 5.00\n\n");
			builder.append("[HKEY_CLASSES_ROOT\\*\\shell\\Paste Content]\n\n");
			builder.append("[HKEY_CLASSES_ROOT\\*\\shell\\Paste Content\\command]\n");
			String curD = Paths.get("").toAbsolutePath().toString();
			curD = curD.replace("\\", "\\\\");
			builder.append("@=\"" + curD + "\\\\bin\\\\paste \\\"" + curD + "\\\\bin\\\" \\\"%1\\\"\"");
			PrintWriter out = null;
			try {
				out = new PrintWriter(PST_REG);
				System.out.println(builder.toString());
				out.print(builder.toString());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				out.close();
			}
		}

		String curD = Paths.get("").toAbsolutePath().toString();
		String cmd = "reg IMPORT " + curD + "\\" + CP_REG;
		String cmd2 = "reg IMPORT " + curD + "\\" + PST_REG;
		System.out.println(cmd);
		System.out.println(cmd2);
		PrintWriter out = null;
		try {
			out = new PrintWriter(CPX_BAT);
			out.print(cmd + "\n");
			out.print(cmd2 + "\n");
			// out.print("pause");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}

		try {
			String localCmd = "powershell.exe -Command \"Start-Process \\\"" + curD + "\\" + CPX_BAT + "\\\" -Verb RunAs\"";
			out = new PrintWriter(CALLCPX_BAT);
			out.print("set PATH=%PATH%;%SYSTEMROOT%\\System32\\WindowsPowerShell\\v1.0\\\n");
			out.print(localCmd);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

}
