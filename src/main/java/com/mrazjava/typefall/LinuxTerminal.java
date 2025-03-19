package com.mrazjava.typefall;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

import com.googlecode.lanterna.terminal.ansi.UnixLikeTTYTerminal;

public class LinuxTerminal extends UnixLikeTTYTerminal {

	public LinuxTerminal() throws IOException {
		this(new File("/dev/pts/5"), System.in, System.out, Charset.defaultCharset(), CtrlCBehaviour.CTRL_C_KILLS_APPLICATION);
	}

	protected LinuxTerminal(File ttyDev, InputStream terminalInput, OutputStream terminalOutput,
			Charset terminalCharset, CtrlCBehaviour terminalCtrlCBehaviour) throws IOException {
		super(ttyDev, terminalInput, terminalOutput, terminalCharset, terminalCtrlCBehaviour);
	}

}
