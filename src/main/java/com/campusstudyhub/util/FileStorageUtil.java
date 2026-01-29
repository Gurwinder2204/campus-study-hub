package com.campusstudyhub.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

/**
 * Utility class for file storage operations.
 */
@Component
public class FileStorageUtil {

    private static final Logger log = LoggerFactory.getLogger(FileStorageUtil.class);

    @Value("${app.upload.dir:uploads}")
    private String uploadDir;

    @Value("${app.upload.max-file-size:10485760}")
    private long maxFileSize;

    private Path notesPath;
    private Path papersPath;

    @PostConstruct
    public void init() {
        try {
            notesPath = Paths.get(uploadDir, "notes").toAbsolutePath().normalize();
            papersPath = Paths.get(uploadDir, "papers").toAbsolutePath().normalize();

            Files.createDirectories(notesPath);
            Files.createDirectories(papersPath);

            log.info("Upload directories initialized: notes={}, papers={}", notesPath, papersPath);
        } catch (IOException e) {
            log.error("Failed to create upload directories", e);
            throw new RuntimeException("Could not create upload directories", e);
        }
    }

    /**
     * Store a note file.
     * 
     * @param file the multipart file to store
     * @return the stored filename (UUID-based)
     * @throws IOException if storage fails
     */
    public String storeNoteFile(MultipartFile file) throws IOException {
        return storeFile(file, notesPath);
    }

    /**
     * Store a question paper file.
     * 
     * @param file the multipart file to store
     * @return the stored filename (UUID-based)
     * @throws IOException if storage fails
     */
    public String storePaperFile(MultipartFile file) throws IOException {
        return storeFile(file, papersPath);
    }

    /**
     * Store a file in the specified directory.
     * 
     * @param file      the multipart file to store
     * @param targetDir the target directory
     * @return the stored filename
     * @throws IOException if storage fails
     */
    private String storeFile(MultipartFile file, Path targetDir) throws IOException {
        validateFile(file);

        String originalFilename = file.getOriginalFilename();
        String extension = getFileExtension(originalFilename);
        String storedFilename = UUID.randomUUID().toString() + extension;

        Path targetPath = targetDir.resolve(storedFilename);
        Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

        log.info("File stored: {} -> {}", originalFilename, targetPath);
        return storedFilename;
    }

    /**
     * Validate the uploaded file.
     * 
     * @param file the file to validate
     * @throws IllegalArgumentException if validation fails
     */
    public void validateFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File is empty or null");
        }

        if (file.getSize() > maxFileSize) {
            throw new IllegalArgumentException(
                    "File size exceeds maximum allowed size of " + (maxFileSize / (1024 * 1024)) + "MB");
        }

        String contentType = file.getContentType();
        if (contentType == null || !contentType.equals("application/pdf")) {
            throw new IllegalArgumentException("Only PDF files are allowed. Received: " + contentType);
        }

        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null || !originalFilename.toLowerCase().endsWith(".pdf")) {
            throw new IllegalArgumentException("File must have .pdf extension");
        }
    }

    /**
     * Get the file extension from filename.
     * 
     * @param filename the original filename
     * @return the file extension including the dot
     */
    private String getFileExtension(String filename) {
        if (filename == null || !filename.contains(".")) {
            return "";
        }
        return filename.substring(filename.lastIndexOf("."));
    }

    /**
     * Get the path to a note file.
     * 
     * @param storedFilename the stored filename
     * @return the full path to the file
     */
    public Path getNoteFilePath(String storedFilename) {
        return notesPath.resolve(storedFilename);
    }

    /**
     * Get the path to a question paper file.
     * 
     * @param storedFilename the stored filename
     * @return the full path to the file
     */
    public Path getPaperFilePath(String storedFilename) {
        return papersPath.resolve(storedFilename);
    }

    /**
     * Delete a note file.
     * 
     * @param storedFilename the stored filename
     * @return true if deleted successfully
     */
    public boolean deleteNoteFile(String storedFilename) {
        return deleteFile(notesPath.resolve(storedFilename));
    }

    /**
     * Delete a question paper file.
     * 
     * @param storedFilename the stored filename
     * @return true if deleted successfully
     */
    public boolean deletePaperFile(String storedFilename) {
        return deleteFile(papersPath.resolve(storedFilename));
    }

    /**
     * Delete a file at the specified path.
     * 
     * @param filePath the path to the file
     * @return true if deleted successfully
     */
    private boolean deleteFile(Path filePath) {
        try {
            if (Files.exists(filePath)) {
                Files.delete(filePath);
                log.info("File deleted: {}", filePath);
                return true;
            }
            log.warn("File not found for deletion: {}", filePath);
            return false;
        } catch (IOException e) {
            log.error("Failed to delete file: {}", filePath, e);
            return false;
        }
    }

    /**
     * Get the notes directory path.
     */
    public Path getNotesPath() {
        return notesPath;
    }

    /**
     * Get the papers directory path.
     */
    public Path getPapersPath() {
        return papersPath;
    }
}
