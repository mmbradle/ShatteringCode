package com.fpx;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;

public class TempDirectory {
    /** The temporary directory */
    private final File directory;

    /**
     * Create a TempDirectory in the system temp folder with a random file name.
     * This will have to be explicitly deleted with deleteDirectory()
     */
    public TempDirectory() throws IOException {
        this(createRandomString());
    }

    /**
     * Create a TempDirectory in the system temp folder named tempDirName
     * This will have to be explicitly deleted with deleteDirectory()
     */
    public TempDirectory(final String tempDirName) throws IOException {
        final String directoryPath = System.getProperty("java.io.tmpdir") + tempDirName;
        this.directory = new File(directoryPath);

        if (this.directory.exists() && this.directory.isDirectory()) {
            // Directory already exists, probably left over from the last run. Delete all contents and continue.
            deleteContents(this.directory);
            return;
        } else if (this.directory.exists() && !this.directory.isDirectory()) {
            // Error condition, a file with this name already exists.
            throw new IOException("File name already exists. Could not create directory at: " + directoryPath);
        } else if (!this.directory.exists()) {
            // Expected case: create the temp dir
            final boolean directoryCreated = (new File(directoryPath)).mkdirs(); // TODO mkdir or mkdirs?
            if (!directoryCreated) {
                throw new IOException("Could not create directory at: " + directoryPath);
            }
        } else {
            assert false : "Unexpected case";
        }
    }

    /** Make sure to call this at the end of the test */
    public boolean deleteDirectory() {
        recursiveDelete(this.directory);
        return !this.directory.exists();
    }

    /** Deletes all contents of the passed in folder, but not the folder itself */
    public static void deleteContents(final File file) {
        File[] files= file.listFiles();
        if (null != files) {
            for (File subFile : files) {
                recursiveDelete(subFile);
            }
        }
    }

    /** Tests whether the file or directory exists. */
    public boolean exists() {
        return this.directory.exists();
    }

    /** Returns a new file with a random name under the temprorary folder. */
    public File newFile() throws IOException {
        return newFile(createRandomString());
    }

    /** Returns a new file with the given name under the temporary folder. */
    public File newFile(final String fileName) throws IOException {
        File file= new File(this.directory, fileName);
        if (!file.createNewFile()) {
            // File already exists
            throw new IOException("File already exists: " + file.getAbsolutePath());
        }
        return file;
    }

    /** Returns a new fresh folder with a random name under the temporary folder. */
    public File newFolder() throws IOException {
        return newFolder(createRandomString());
    }

    /** Returns a new fresh folder with the given name under the temporary folder. */
    public File newFolder(final String folderName) throws IOException {
        File file= new File(this.directory, folderName);
        if (!file.mkdir()) {
            // File already exists
            throw new IOException("Directory creation failed: " + file.getAbsolutePath());
        }
        return file;
    }

    /** Deletes the passed in File/Folder and all subfiles/subfolders that may exist */
    private static void recursiveDelete(final File file) {
        File[] files= file.listFiles();
        if (null != files) {
            for (File subFile : files) {
                recursiveDelete(subFile);
            }
        }
        file.delete();

        int attempts = 1;
        final int maxAttempts = 10;
        while(file.exists() && attempts < maxAttempts) {
            file.delete();
            attempts++;
        }

        if (attempts >= maxAttempts) {
            System.out.println("Could not delete file at: " + file.getAbsolutePath());
        }
    }

    @Override
    public String toString() {
        return this.directory.getAbsolutePath();
    }

    /** Helper method to get a random string that is used for naming random files */
    private final static String createRandomString() {
        // Grabbed this online, not exactly sure how it works
        return new BigInteger(130, new Random()).toString(32);
    }
}
