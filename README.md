<p align="center">
  <img src="https://paste.sk/images/vCoozf.png" />
</p>

## Quick description
Specialized Drops is advanced plugin to configure your own custom drops in minecraft...
Resource has lot of functions that You can use in your configuration.
You can created your own items, that can drop from block or entity.
Do you want to create specialized group for selected world, player, permission or a few more conditions You want to use..
You can also use Multiple conditions in plugin configuration.. This functions is named "Override".
## Quick startup
If You're interested of our source, here is description how to start plugin on your server...
- Download SpecializedDrops.jar
- Download TheAPI.jar
- Put jar files into /plugins folder
- Start server

Is there any conditions to use this source?
No, but if You want to use it correctly, make sure about
- Your server version (Plugin supports 1.8 - Latest)
- TheAPI in /plugins folder (TheAPI is depend for this source)

<p align="center">
  <img src="https://paste.sk/images/fRRQUY.png"/>
</p>

## Read this!
- Every our configuration file has detailed comments to every Function that You can configure...
- If you want to reset plugin's folder -> Just remove /SpecializedDrops and restart server!
- We have a lot of functions, and a lot of customizable modules, that's why we have a few configuration files...
- We'll be glad if You write us your new ideas to add to this source!
- If You've problems with configuration, make sure that You've read all comments describes your problem...
- If comments don't work, contact us on our Discord!

<h3 align="center">Our Discord:</h3>
<a href="https://discord.gg/Xd68nahk">
<p  align="center"> 
<img alt="Qries" src="https://paste.sk/images/MjWrSR.png">
</p></a>

## Configuration files
### File settings.yml
In settings yml you can set-up plugin function... In every configuration in this plugin
we've written detailed description to every function. So You don't have to open our sites
on editing or managing our source!
```yaml
# Simple Override is simple alternative of advanced Override system...
# (Advanced Override system You can configure in /Overrides folder)
Simple_Override:
  '0':
    # Switch (true/false)
    Enabled: false
    # Put here Entity/Block names
    Editing:
    - "STONE"
    # Put here drop from /Items/...
    # If you want use custom Item... Use: ~{Item:<fileName>}
    # Put FileName with .yml!
    Drop: STONE
    # You can edit chance here
    Chance: 50.0
  '1':
    # Switch (true/false)
    Enabled: false
    # Put here Entity/Block names
    Editing:
    - "SAND"
    # Put here drop from /Items/...
    # If you want use custom Item... Use: ~{Item:<FolderName>/<FileName>}
    # Put FileName with .yml!
    Drop: RED_SAND
    # You can edit chance here
    Chance: 50.0
# Here you can prepare the Selection System
System:
  # If You don't have to, don't edit this option...
  # Here You can edit selection priority
  # Types: | Override: Override system... If is disabled this part will be skipped
  #        | MainSystem: Main selection system... If is disabled part will be skipped tho
  #        | DefaultDrop: Default drop from Block/Entity
  # If Block destroy or Entity kill don't contain conditions in any profile, Overrides part will be skipped
  # and the selection is on MainSystem
  Priorities:
    - Override
    - MainSystem
    - DefaultDrop
    # Here you can configure the Main Selection System
    MainSystem:
    # Setup drops for blocks
      Blocks:
      # Do you want see what Type contains?
      # Check out our Sites for more details...
      # Do not edit type names! Just values...
      # Put directories names from /Items folder!
      # You can also include any other folder...
      # DefaultChance is chance for default drop (non edit)
      # Example:
      #
      # Includes:
      #   - Glasses
      #   - BasedBlocks
      #
      # If You don't want to include anything -> Includes: []
        Undefined_Types:
          Enabled: true
          Folder: "Undefineds"
          DefaultChance: 95.0
          Includes: []
        Glass_Types:
          Enabled: true
          Folder: "Glasses"
          DefaultChance: 95.0
          Includes: []
        Advantage_Types:
          Enabled: true
          Folder: "Advantages"
          DefaultChance: 95.0
          Includes: []
        Based_Block_Types:
          Enabled: true
          Folder: "BasedBlocks"
          DefaultChance: 95.0
          Includes: []
        Design_Types:
          Enabled: true
          Folder: "Designs"
          DefaultChance: 95.0
          Includes: []
        Using_Types:
          Enabled: true
          Folder: "Usings"
          DefaultChance: 95.0
          Includes: []
        Electricity_Types:
          Enabled: true
          Folder: "Electricities"
          DefaultChance: 95.0
          Includes: []
        Plants_Types:
          Enabled: true
          Folder: "Plants"
          DefaultChance: 95.0
          Includes: []
        Snow_Types:
          Enabled: true
          Folder: "Snows"
          DefaultChance: 95.0
          Includes: []
        Dirt_Types:
          Enabled: true
          Folder: "Dirts"
          DefaultChance: 95.0
          Includes: []
        Sand_Types:
          Enabled: true
          Folder: "Sands"
          DefaultChance: 95.0
          Includes: []
        Stone_Types:
          Enabled: true
          Folder: "Stones"
          DefaultChance: 95.0
          Includes: []
        Deep_Stone_Types:
          Enabled: true
          Folder: "DeepStones"
          DefaultChance: 95.0
          Includes: []
        Ore_Types:
          Enabled: true
          Folder: "Ores"
          DefaultChance: 95.0
          Includes: []
        Wood_Types:
          Enabled: true
          Folder: "Woods"
          DefaultChance: 95.0
          Includes: []
        Food_Types:
          Enabled: true
          Folder: "Foods"
          DefaultChance: 95.0
          Includes: []
        # Setup drops for entities
        Entities:
        # Mob types contains every enemy entity
        Mob_Types:
          Enabled: true
          Folder: "Mobs"
          DefaultChance: 95.0
          Includes: []
        # Friendly types contains every friendly entity
        Friendly_Types:
          Enabled: true
          Folder: "Friends"
          DefaultChance: 95.0
          Includes: []
```

### File collections.yml
In this file you can manage Your created collections... This collections you can use in Override function!
```yaml
# Put here list of created collections...
Collections:
  # Name of your collection
  MagicCollection:
    # Folder name of collection
    FolderName: "MagicCollection"
    # You can also use MaxDrops value to define max selected items that will drop from collection
    MaxDrops: 1
    # You can define if selected items will be dropped with or without default drops...
    DropWithDefaults: false
    # Includes means, that you can include other collections
    # into new one. So you don't have to copy & paste items
    # between collections...
    Includes: []
```

### File overrides.yml
Here You can manage our module... We named it Overrides. This function is overriding MainSystem that
We've created... More in plugin description...
```yaml
# Set if overrides is enabled
Enabled: false
Overrides:
  Example:
    # Option if override is enabled
    Enabled: false
    # Select profile with conditions
    Profile: "profile_example.yml"
    # Select drops with Collections module
    Collection: "MagicCollection"
```
## Creative files
We also created two more files, that You can configure to have advanced configuration.
We named it `SpecializedItems.yml` and `SpecializedProfile.yml`!

### SpecializedItems.yml
Here You can see example of your special items! You can configure a lot of things...
```yaml
### | This is an example configuration file
### | This file isn't works in enabled plugin...
### | Do not remove this file to know what all you can create
# You can use 1.13+ ID's or if you want you can also you 1.13- ID's
# 1.13+ -> Like BLACK_WOOL
# 1.13- -> Like 2:2
Material: STONE
# You can use classic colors...
# With 1.16 or higher you can use gradient & rgb
# Gradient example: !{hex-color}Your text!{hex-color}
# Rgb example: #{hex-color}Your text!
Name: "&7Stone"
# Don't use colors in legacy text!
LegacyName: "Example Stone"
# Use '1' in non-stackable items like Sword or Bucket
# If you want use Random... -> Example:
# Amount: ~Random(min, max)
# So -> Amount: ~Random(1,5)
Amount: "~Random(1,9)"
# CustomModelData value is for custom texture-pack usage...
# You're not using texture pack? Throw here 0
CustomModelData: 0
# Put texts that you want to have in lore...
# You can use Colors... With 1.16 or higher Gradient & Rgb
Lore: []
# List of enchantments you can find on plugin pages
# Example: "{enchantment}:{intensity}"
Enchantments: []
# Write DropPercentage like: 1/100 which means 1%
# Map: | 1/1000 - 0.1%
#      | 1/10000 - 0.01%
#      | 1/100000 - 0.001%
#      | 1/1000000 - 0.0001%
DropPercentage: "50/1"
# If you enable this option... This item will drop with default drop...
IsAdditional: false
# In every line, message you can use Colors... With 1.16 or higher Gradient & Rgb
# In custom commands you can't use our API, search information about plugin command
# Placeholders: {x} {y} {z} -> Location of drop
#               {Player} -> Player's name
#               {MinedBlock} -> Name of MinedBlock
#               {DropLegacyName} -> Name of drop (Legacy)
#               {DropColorizedName} -> Name of drop (Colorized)
DropEvents:
  Title:
    Line1: "Congratulations!"
    Line2: "You've received special drop..."
    fade-in: 10
    keep: 40
    fade-out: 10
  Broadcast: "{Player} has received {DropLegacyName} from {MinedBlock}..."
  Message: "You've received {DropLegacyName} from {MinedBlock}..."
  CustomCommands:
    - "/say Manage your CustomCommands..."
```

### SpecializedProfile.yml
Here You can define conditions, and use this profiles in Override function...
```yaml
# Write here a Legacy Name of a Profile
Legacy_Name: "Example Name"
# Condition settings
Conditions:
  # You can override Block or Entity
  Override: Block
  # Put name of Material, if you selected Override: Entity, then put here EntityType name
  Types:
    - STONE
  # Select factor for destroy time
  # Options: DAY, NIGHT
  Time: DAY
  # Do you want to set drop to one selected group?
  # Add permissions to profile...
  Permissions:
    - "special.destroy"
  # Select world where item can be dropped
  World: "default"
  # Value if player have to sneak to drop item
  Sneaking: false
  # Select Biome where you want to drop item...
  Biome: "Plants"
  # Select if item can be dropped only if is raining in a world...
  Raining: true
  # You can define special players with Destroyed value
  Destroyer:
    - "M3II0"
    - "StraikerinaCZ"
  # Looking types: DOWN, STRAIGHT, UP
  Looking: UP
  # Set value, if profile can use Fortune or Looting value
  Luck: false
```

<h3 align="center">Looking for Types, Enchantments or something else..?</h3>
<a href="https://github.com/TheDevTec/SpecializedDrops/blob/main/OTHERS.md">
<p  align="center"> 
<img alt="Qries" src="https://paste.sk/images/WujGEP.png">
</p></a>