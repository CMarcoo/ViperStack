package me.thevipershow.viperstack.packages.classes;

import java.util.EnumMap;
import java.util.Map;

public enum BukkitPackage {
    PACKAGE;

    public enum PackageType {
        ITEM_STACK("org.bukkit.inventory.ItemStack"),
        ITEM_META("org.bukkit.inventory.meta.ItemMeta"),
        MATERIAL("org.bukkit.Material");

        private final String $package;

        PackageType(final String $package) {
            this.$package = $package;
        }

        public final String get$package() {
            return $package;
        }
    }

    private final Map<PackageType, Class<?>> reflectionCache = new EnumMap<>(PackageType.class);

    public final Class<?> reflectInto(final PackageType packageType) {
        Class<?> clazz = reflectionCache.get(packageType);
        if (clazz == null) {
            try {
                clazz = Class.forName(packageType.$package);
                reflectionCache.put(packageType, clazz);
            } catch (final ClassNotFoundException exception) {
                exception.printStackTrace();
            }
        }
        return clazz;
    }
}
