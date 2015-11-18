package org.wso2.carbon.connector.util;

import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemOptions;
import org.apache.commons.vfs2.provider.ftp.FtpFileSystemConfigBuilder;
import org.apache.commons.vfs2.provider.ftps.FtpsFileSystemConfigBuilder;
import org.apache.commons.vfs2.provider.sftp.SftpFileSystemConfigBuilder;

public class FTPSiteUtils {
	/**
	 * Get the default options for File system
	 * 
	 * @return
	 * @throws FileSystemException
	 */
	public static FileSystemOptions createDefaultOptions() throws FileSystemException {
		FileSystemOptions opts = new FileSystemOptions();

		// SSH Key checking
		SftpFileSystemConfigBuilder.getInstance().setStrictHostKeyChecking(opts, "no");

		// Root directory set to user home
		SftpFileSystemConfigBuilder.getInstance().setUserDirIsRoot(opts, false);

		// Timeout is count by Milliseconds

		SftpFileSystemConfigBuilder.getInstance().setTimeout(opts, 100000);

		FtpFileSystemConfigBuilder.getInstance().setPassiveMode(opts, true);

		FtpFileSystemConfigBuilder.getInstance().setSoTimeout(opts, 100000);

		FtpsFileSystemConfigBuilder.getInstance().setPassiveMode(opts, true);
		
		return opts;

	}
}