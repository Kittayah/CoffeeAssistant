# Coffee Assistant 

CoffeeAssistant is a small school project written in... two days? It is a Java-based desktop application designed to simplify manual coffee brewing process by following recipes step by step. It can act as a recipe storage as well.
Anyway, it ain't much but you can brew delicious coffee with it's help

### Getting Started

1.  **Prerequisites:**
    *   Java Development Kit (JDK) 21 or basically whatever
    *   Maven (for building the project)
    *   An IDE like IntelliJ IDEA or Eclipse (optional, but recommended)

### Using CoffeeAssistant

*   **Main Menu:**
    *   **Start Brewing:** Begin the guided brewing process.
    *   **Settings:** Manage your coffee grinder settings.
    *   **Manage Recipes:** Create, edit, save, and load your coffee recipes.

*   **Brewing Screen:**
    *   Select a recipe from the dropdown list.
    *   Click "Start" to begin the brewing process.
    *   Follow the step-by-step instructions and timer for each stage of the brew.

*   **Recipe Management:**
    *   Create new recipes by entering details like name, coffee type, grind size, and steps.
    *   Edit existing recipes to refine your brewing techniques.
    *   Save and load recipes for future use.

*   **Grinder Settings:**
    *   Add your coffee grinders and their recommended settings for different brewing methods.
    *   Select your default grinder for quick access during brewing.

 ### Additional notes:

Yeah, `settings.xml` allows you to store your recipes, this is the baseline, the rest will be automatically stored in `.coffeebag` either in your project workspace or appdata.

There's a pretty easy structure:

*   **Grinder Settings:** Details about the coffee grinders you use, such as their names, descriptions, and recommended grind settings for different brewing methods (e.g., Aeropress, Espresso, French Press).
*   **Coffee Recipes:** Your personalized coffee recipes, including the name, coffee type, grind size, water amount, and step-by-step brewing instructions.

```xml
<settings>
    <grinders>
        <grinder>
            <name>...</name>
            <description>...</description>
            <grindSettings>
                <setting>
                    <brewingMethod>...</brewingMethod>
                    <min>...</min>
                    <max>...</max>
                </setting>
                </grindSettings>
        </grinder>
        </grinders>
    <recipes>
        <recipe>
            <name>...</name>
            <description>...</description>
            <coffee>...</coffee>
            <grind>...</grind>
            <totalWater>...</totalWater>
            <totalCoffee>...</totalCoffee>
            <steps>
                <step>
                    <duration>...</duration>
                    <water>...</water>
                    <stepInfo>...</stepInfo>
                </step>
                </steps>
        </recipe>
        </recipes>
</settings>
```

When the application starts, the `SettingsService` class loads the data from `settings.xml`, some deserialization is going and then you can directly interact with the data.

