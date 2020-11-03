# ViperStack
Library for ItemStacks and related by me!
[![](https://jitpack.io/v/TheViperShow/ViperStack.svg)](https://jitpack.io/#TheViperShow/ViperStack)

```java
// Code sample to generate a cool simple item:
        final Object obj = new ViperStackBuilder(Material.EMERALD)
                .amount(16)
                .name(ChatColor.GREEN + "Kryptonite")
                .lore(ChatColor.GREEN + "A rare mineral, ", ChatColor.DARK_GREEN + "it seems dangerous...")
                .build().bukkitStack();

        final ItemStack kryptonite = (ItemStack) obj;
        event.getPlayer().getInventory().addItem(kryptonite);
```
