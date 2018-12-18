import java.io.File;

class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Mods <newDirPath>");
            System.exit(-1);
        }

        File dir = new File(args[0]);
        if (dir.exists()) {
                System.out.println((dir.delete() ? "Deleted " :
                                    "Could not delete ") + dir.getPath());
        }
        if (dir.mkdirs()) {
            System.out.println("Created directory " + dir.getAbsolutePath());
            File subdir = new File(dir, "newSub");

            if (subdir.mkdir()) {
                System.out.println("Created subdirectory " + 
                                   subdir.getAbsolutePath());
                System.out.println((subdir.delete() ? "Deleted " :
                                    "Could not delete ") + subdir.getPath());
            }
            else
                System.out.println("Could not create subdirectory " +
                                   subdir.getAbsolutePath());
            System.out.println((dir.delete() ? "Deleted " :
                                "Could not delete ") + dir.getPath());
        } else {
            System.out.println("Could not create directory " +
                               dir.getAbsolutePath());
        }
    }
}
