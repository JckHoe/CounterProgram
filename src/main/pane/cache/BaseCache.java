package main.pane.cache;

import java.io.File;
import java.util.Optional;

public abstract class BaseCache {
    public abstract Optional<File> readFakeCache();
}
