package  com.burningsulphur.chewy_cheeses;

import com.github.alexthe666.rats.registry.RatsItemRegistry;
import com.mojang.logging.LogUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.satisfy.meadow.core.block.CheeseBlock;
import net.yirmiri.dungeonsdelight.core.registry.DDItems;
import org.slf4j.Logger;

import umpaz.brewinandchewin.common.block.*;
import umpaz.brewinandchewin.common.registry.BnCBlocks;
import umpaz.brewinandchewin.common.registry.BnCFluids;
import umpaz.brewinandchewin.common.registry.BnCItems;
import net.satisfy.meadow.core.registry.ObjectRegistry;

import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import umpaz.brewinandchewin.BrewinAndChewin;
import umpaz.brewinandchewin.common.fluid.AlcoholFluidType;
import umpaz.brewinandchewin.common.fluid.CheeseFluidType;
import umpaz.brewinandchewin.common.fluid.HoneyFluidType;
import umpaz.brewinandchewin.common.fluid.MeadFluidType;

import net.jadenxgamer.netherexp.registry.item.JNEItems;

import static net.satisfy.meadow.core.util.GeneralUtil.registerWithItem;
import static net.yirmiri.dungeonsdelight.core.registry.DDCreativeTabs.DUNGEONSDELIGHT;
import net.yirmiri.dungeonsdelight.common.util.DDProperties;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ChewyCheeses.MOD_ID)
public class ChewyCheeses
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "chewy_cheeses";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "examplemod" namespace
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);
    // Create a Deferred Register to hold Items which will all be registered under the "examplemod" namespace
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);
    // Create a Deferred Register to hold CreativeModeTabs which will all be registered under the "examplemod" namespace
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);

    public static final RegistryObject<Item> TAB_ITEM = ITEMS.register("tab_item", () -> new Item(new Item.Properties()));

    // Create a DeferredRegister for the items
    public static final DeferredRegister<Item> OPTIONAL_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);
    // Create a DeferredRegister for the blocks
    public static final DeferredRegister<Block> OPTIONAL_BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);

    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, ChewyCheeses.MOD_ID);
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, ChewyCheeses.MOD_ID);



    /* ----------------------------------------------notes for adding a new cheese ----------------------------------------------------

    register block
    register unripe block
    register item for blocks
    add new cheese fluid type to cheese fluid
    register fluid
    block states
    block models
    coster model (in brewin and chewin file) - it is just the corner of cheese to go on a coaster, uses the cheese slice item from the mods
    fluid item display
    lang
    block textures
    recipes
    tags

     ---------------------------------------------------------------------------------------------------------------------------------*/

    //rats mod
    //normal cheese
    public static final RegistryObject<Block> UNRIPE_RAT_LEICESTER_CHEESE_WHEEL = ModList.get().isLoaded("rats") ? OPTIONAL_BLOCKS.register("unripe_rat_leicester_cheese_wheel", () -> new UnripeCheeseWheelBlock(ChewyCheeses.RAT_LEICESTER_CHEESE_WHEEL , Block.Properties.copy(Blocks.CAKE))) : null;
    public static final RegistryObject<Block> RAT_LEICESTER_CHEESE_WHEEL = ModList.get().isLoaded("rats") ? OPTIONAL_BLOCKS.register("rat_leicester_cheese_wheel", () -> new CheeseWheelBlock(RatsItemRegistry.CHEESE , Block.Properties.copy(Blocks.CAKE))) : null;
    public static final RegistryObject<Item> UNRIPE_RAT_LEICESTER_CHEESE_WHEEL_ITEM = ModList.get().isLoaded("rats") ? OPTIONAL_ITEMS.register("unripe_rat_leicester_cheese_wheel", () -> new BlockItem(UNRIPE_RAT_LEICESTER_CHEESE_WHEEL.get(), new Item.Properties().stacksTo(16))): null;
    public static final RegistryObject<Item> RAT_LEICESTER_CHEESE_WHEEL_ITEM = ModList.get().isLoaded("rats") ? OPTIONAL_ITEMS.register("rat_leicester_cheese_wheel", () -> new BlockItem(RAT_LEICESTER_CHEESE_WHEEL.get(), new Item.Properties().stacksTo(16))): null;

    public static final RegistryObject<FluidType> RAT_LEICESTER_CHEESE_FLUID_TYPE = FLUID_TYPES.register("rat_leicester_cheese_type", () -> new CheeseFluid("rat_leicester"));
    public static final RegistryObject<FlowingFluid> RAT_LEICESTER_CHEESE = FLUIDS.register("rat_leicester_cheese", () -> new ForgeFlowingFluid.Source(ChewyCheeses.RAT_LEICESTER_CHEESE_FLUID_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_RAT_LEICESTER_CHEESE = FLUIDS.register("flowing_rat_leicester_cheese", () -> new ForgeFlowingFluid.Flowing(ChewyCheeses.RAT_LEICESTER_CHEESE_FLUID_PROPERTIES));
    public static final ForgeFlowingFluid.Properties RAT_LEICESTER_CHEESE_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(RAT_LEICESTER_CHEESE_FLUID_TYPE, RAT_LEICESTER_CHEESE, FLOWING_RAT_LEICESTER_CHEESE);

// blue cheese
    public static final RegistryObject<Block> UNRIPE_BLUE_CHEESE_WHEEL = ModList.get().isLoaded("rats") ? OPTIONAL_BLOCKS.register("unripe_blue_cheese_wheel", () -> new UnripeCheeseWheelBlock(ChewyCheeses.BLUE_CHEESE_WHEEL , Block.Properties.copy(Blocks.CAKE))) : null;
    public static final RegistryObject<Block> BLUE_CHEESE_WHEEL = ModList.get().isLoaded("rats") ? OPTIONAL_BLOCKS.register("blue_cheese_wheel", () -> new CheeseWheelBlock(RatsItemRegistry.BLUE_CHEESE , Block.Properties.copy(Blocks.CAKE))) : null;
    public static final RegistryObject<Item> UNRIPE_BLUE_CHEESE_WHEEL_ITEM = ModList.get().isLoaded("rats") ? OPTIONAL_ITEMS.register("unripe_blue_cheese_wheel", () -> new BlockItem(UNRIPE_BLUE_CHEESE_WHEEL.get(), new Item.Properties().stacksTo(16))): null;
    public static final RegistryObject<Item> BLUE_CHEESE_WHEEL_ITEM = ModList.get().isLoaded("rats") ? OPTIONAL_ITEMS.register("blue_cheese_wheel", () -> new BlockItem(BLUE_CHEESE_WHEEL.get(), new Item.Properties().stacksTo(16))): null;

    public static final RegistryObject<FluidType> BLUE_CHEESE_FLUID_TYPE = FLUID_TYPES.register("blue_cheese_type", () -> new CheeseFluid("blue"));
    public static final RegistryObject<FlowingFluid> BLUE_CHEESE = FLUIDS.register("blue_cheese", () -> new ForgeFlowingFluid.Source(ChewyCheeses.BLUE_CHEESE_FLUID_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_BLUE_CHEESE = FLUIDS.register("flowing_blue_cheese", () -> new ForgeFlowingFluid.Flowing(ChewyCheeses.BLUE_CHEESE_FLUID_PROPERTIES));
    public static final ForgeFlowingFluid.Properties BLUE_CHEESE_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(BLUE_CHEESE_FLUID_TYPE, BLUE_CHEESE, FLOWING_BLUE_CHEESE);

    // nether cheese
    public static final RegistryObject<Block> UNRIPE_NETHER_CHEESE_WHEEL = ModList.get().isLoaded("rats") ? OPTIONAL_BLOCKS.register("unripe_nether_cheese_wheel", () -> new UnripeCheeseWheelBlock(ChewyCheeses.NETHER_CHEESE_WHEEL , Block.Properties.copy(Blocks.CAKE))) : null;
    public static final RegistryObject<Block> NETHER_CHEESE_WHEEL = ModList.get().isLoaded("rats") ? OPTIONAL_BLOCKS.register("nether_cheese_wheel", () -> new CheeseWheelBlock(RatsItemRegistry.NETHER_CHEESE , Block.Properties.copy(Blocks.CAKE))) : null;
    public static final RegistryObject<Item> UNRIPE_NETHER_CHEESE_WHEEL_ITEM = ModList.get().isLoaded("rats") ? OPTIONAL_ITEMS.register("unripe_nether_cheese_wheel", () -> new BlockItem(UNRIPE_NETHER_CHEESE_WHEEL.get(), new Item.Properties().stacksTo(16))): null;
    public static final RegistryObject<Item> NETHER_CHEESE_WHEEL_ITEM = ModList.get().isLoaded("rats") ? OPTIONAL_ITEMS.register("nether_cheese_wheel", () -> new BlockItem(NETHER_CHEESE_WHEEL.get(), new Item.Properties().stacksTo(16))): null;

    public static final RegistryObject<FluidType> NETHER_CHEESE_FLUID_TYPE = FLUID_TYPES.register("nether_cheese_type", () -> new CheeseFluid("nether"));
    public static final RegistryObject<FlowingFluid> NETHER_CHEESE = FLUIDS.register("nether_cheese", () -> new ForgeFlowingFluid.Source(ChewyCheeses.NETHER_CHEESE_FLUID_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_NETHER_CHEESE = FLUIDS.register("flowing_nether_cheese", () -> new ForgeFlowingFluid.Flowing(ChewyCheeses.NETHER_CHEESE_FLUID_PROPERTIES));
    public static final ForgeFlowingFluid.Properties NETHER_CHEESE_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(NETHER_CHEESE_FLUID_TYPE, NETHER_CHEESE, FLOWING_NETHER_CHEESE);

// jaden's nether expansion
// glow cheese

    public static final RegistryObject<Block> UNRIPE_GLOW_CHEESE_WHEEL = ModList.get().isLoaded("netherexp") ? OPTIONAL_BLOCKS.register("unripe_glow_cheese_wheel", () -> new UnripeCheeseWheelBlock(ChewyCheeses.GLOW_CHEESE_WHEEL , Block.Properties.copy(Blocks.CAKE).lightLevel((p_152607_) -> 10))) : null;
    public static final RegistryObject<Block> GLOW_CHEESE_WHEEL = ModList.get().isLoaded("netherexp") ? OPTIONAL_BLOCKS.register("glow_cheese_wheel", () -> new CheeseWheelBlock(JNEItems.GLOWCHEESE , Block.Properties.copy(Blocks.CAKE).lightLevel((p_152607_) -> 15))) : null;
    public static final RegistryObject<Item> UNRIPE_GLOW_CHEESE_WHEEL_ITEM = ModList.get().isLoaded("netherexp") ? OPTIONAL_ITEMS.register("unripe_glow_cheese_wheel", () -> new BlockItem(UNRIPE_GLOW_CHEESE_WHEEL.get(), new Item.Properties().stacksTo(16))): null;
    public static final RegistryObject<Item> GLOW_CHEESE_WHEEL_ITEM = ModList.get().isLoaded("netherexp") ? OPTIONAL_ITEMS.register("glow_cheese_wheel", () -> new BlockItem(GLOW_CHEESE_WHEEL.get(), new Item.Properties().stacksTo(16))): null;

    public static final RegistryObject<FluidType> GLOW_CHEESE_FLUID_TYPE = FLUID_TYPES.register("glow_cheese_type", () -> new CheeseFluid("glow"));
    public static final RegistryObject<FlowingFluid> GLOW_CHEESE = FLUIDS.register("glow_cheese", () -> new ForgeFlowingFluid.Source(ChewyCheeses.GLOW_CHEESE_FLUID_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_GLOW_CHEESE = FLUIDS.register("flowing_glow_cheese", () -> new ForgeFlowingFluid.Flowing(ChewyCheeses.GLOW_CHEESE_FLUID_PROPERTIES));
    public static final ForgeFlowingFluid.Properties GLOW_CHEESE_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(GLOW_CHEESE_FLUID_TYPE, GLOW_CHEESE, FLOWING_GLOW_CHEESE);

// dungeons delight

    public static final RegistryObject<Block> UNRIPE_WARDENZOLA_CHEESE_WHEEL = ModList.get().isLoaded("dungeonsdelight") ? OPTIONAL_BLOCKS.register("unripe_wardenzola_cheese_wheel", () -> new UnripeCheeseWheelBlock(ChewyCheeses.WARDENZOLA_CHEESE_WHEEL , Block.Properties.copy(Blocks.CAKE))) : null;
    public static final RegistryObject<Block> WARDENZOLA_CHEESE_WHEEL = ModList.get().isLoaded("dungeonsdelight") ? OPTIONAL_BLOCKS.register("wardenzola_cheese_wheel", () -> new CheeseWheelBlock(DDItems.WARDENZOLA_CRUMBLES, Block.Properties.copy(Blocks.CAKE))) : null;
    public static final RegistryObject<Item> UNRIPE_WARDENZOLA_CHEESE_WHEEL_ITEM = ModList.get().isLoaded("dungeonsdelight") ? OPTIONAL_ITEMS.register("unripe_wardenzola_cheese_wheel", () -> new BlockItem(UNRIPE_WARDENZOLA_CHEESE_WHEEL.get(), new Item.Properties().stacksTo(16).rarity(DDProperties.MONSTER))): null;
    public static final RegistryObject<Item> WARDENZOLA_CHEESE_WHEEL_ITEM = ModList.get().isLoaded("dungeonsdelight") ? OPTIONAL_ITEMS.register("wardenzola_cheese_wheel", () -> new BlockItem(WARDENZOLA_CHEESE_WHEEL.get(), new Item.Properties().stacksTo(16).rarity(DDProperties.MONSTER))): null;

    public static final RegistryObject<FluidType> WARDENZOLA_CHEESE_FLUID_TYPE = FLUID_TYPES.register("wardenzola_cheese_type", () -> new CheeseFluid("wardenzola"));
    public static final RegistryObject<FlowingFluid> WARDENZOLA_CHEESE = FLUIDS.register("wardenzola_cheese", () -> new ForgeFlowingFluid.Source(ChewyCheeses.WARDENZOLA_CHEESE_FLUID_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_WARDENZOLA_CHEESE = FLUIDS.register("flowing_wardenzola_cheese", () -> new ForgeFlowingFluid.Flowing(ChewyCheeses.WARDENZOLA_CHEESE_FLUID_PROPERTIES));
    public static final ForgeFlowingFluid.Properties WARDENZOLA_CHEESE_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(WARDENZOLA_CHEESE_FLUID_TYPE, WARDENZOLA_CHEESE, FLOWING_WARDENZOLA_CHEESE);



    /*
    //Lets do meadow compat. come back to this
    public static final RegistryObject<Block> UNRIPE_AMETHYST_CHEESE_WHEEL = BLOCKS.register("unripe_amethyst_cheese_wheel", () -> new UnripeCheeseWheelBlock(ObjectRegistry.AMETHYST_CHEESE_BLOCK , Block.Properties.copy(Blocks.CAKE)));

    public static final RegistryObject<Block> TEST_AMETHYST_CHEESE_WHEEL = BLOCKS.register("test_amethyst_cheese_wheel", () -> new CheeseWheelBlock(ObjectRegistry.PIECE_OF_AMETHYST_CHEESE , Block.Properties.copy(Blocks.CAKE)));


    public static final RegistryObject<Item> UNRIPE_AMETHYST_CHEESE_WHEEL_ITEM = ITEMS.register("unripe_amethyst_cheese_wheel", () -> new BlockItem(UNRIPE_AMETHYST_CHEESE_WHEEL.get(), new Item.Properties().stacksTo(16)));
    public static final RegistryObject<Item> TEST_AMETHYST_CHEESE_WHEEL_ITEM = ITEMS.register("test_amethyst_cheese_wheel", () -> new BlockItem(TEST_AMETHYST_CHEESE_WHEEL.get(), new Item.Properties().stacksTo(16)));

     */


    /*
    // Creates a new food item with the id "examplemod:example_id", nutrition 1 and saturation 2
    public static final RegistryObject<Item> EXAMPLE_ITEM = ITEMS.register("example_item", () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
            .alwaysEat().nutrition(1).saturationMod(2f).build())));
*/
    // Creates a creative tab with the id "examplemod:example_tab" for the example item, that is placed after the combat tab

    public static final RegistryObject<CreativeModeTab> CHEWY_CHEESES_TAB  = CREATIVE_MODE_TABS.register("chewy_cheeses", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
            .title(Component.translatable("item_group." + MOD_ID + ".tab"))
            .icon(() -> TAB_ITEM.get().getDefaultInstance())
            .build());

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        if (UNRIPE_RAT_LEICESTER_CHEESE_WHEEL_ITEM != null && event.getTab() == CHEWY_CHEESES_TAB.get()) {
            event.accept(UNRIPE_RAT_LEICESTER_CHEESE_WHEEL_ITEM.get());
        }
        if (RAT_LEICESTER_CHEESE_WHEEL_ITEM != null && event.getTab() == CHEWY_CHEESES_TAB.get()) {
            event.accept(RAT_LEICESTER_CHEESE_WHEEL_ITEM.get());
        }
        if (UNRIPE_BLUE_CHEESE_WHEEL_ITEM != null && event.getTab() == CHEWY_CHEESES_TAB.get()) {
            event.accept(UNRIPE_BLUE_CHEESE_WHEEL_ITEM.get());
        }
        if (BLUE_CHEESE_WHEEL_ITEM != null && event.getTab() == CHEWY_CHEESES_TAB.get()) {
            event.accept(BLUE_CHEESE_WHEEL_ITEM.get());
        }
        if (UNRIPE_NETHER_CHEESE_WHEEL_ITEM != null && event.getTab() == CHEWY_CHEESES_TAB.get()) {
            event.accept(UNRIPE_NETHER_CHEESE_WHEEL_ITEM.get());
        }
        if (NETHER_CHEESE_WHEEL_ITEM != null && event.getTab() == CHEWY_CHEESES_TAB.get()) {
            event.accept(NETHER_CHEESE_WHEEL_ITEM.get());
        }
        if (UNRIPE_GLOW_CHEESE_WHEEL_ITEM != null && event.getTab() == CHEWY_CHEESES_TAB.get()) {
            event.accept(UNRIPE_GLOW_CHEESE_WHEEL_ITEM.get());
        }
        if (GLOW_CHEESE_WHEEL_ITEM != null && event.getTab() == CHEWY_CHEESES_TAB.get()) {
            event.accept(GLOW_CHEESE_WHEEL_ITEM.get());
        }
        if (UNRIPE_WARDENZOLA_CHEESE_WHEEL_ITEM != null && event.getTab() == CHEWY_CHEESES_TAB.get()) {
        event.accept(UNRIPE_WARDENZOLA_CHEESE_WHEEL_ITEM.get());
        }
        if (WARDENZOLA_CHEESE_WHEEL_ITEM != null && event.getTab() == CHEWY_CHEESES_TAB.get()) {
            event.accept(WARDENZOLA_CHEESE_WHEEL_ITEM.get());
        }
        //removing and adding it to their tab
        if (WARDENZOLA_CHEESE_WHEEL_ITEM != null){ // a way of testing if the mod is actually installed
            event.getEntries().remove(DDItems.WARDENZOLA.get().getDefaultInstance());
        }
        if (UNRIPE_WARDENZOLA_CHEESE_WHEEL_ITEM != null && event.getTab() == DUNGEONSDELIGHT.get()) {
            event.accept(UNRIPE_WARDENZOLA_CHEESE_WHEEL_ITEM.get());
        }
        if (WARDENZOLA_CHEESE_WHEEL_ITEM != null && event.getTab() == DUNGEONSDELIGHT.get()) {
            event.accept(WARDENZOLA_CHEESE_WHEEL_ITEM.get());
        }
    }

    public ChewyCheeses(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register the Deferred Register to the mod event bus so blocks get registered
        BLOCKS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so items get registered
        ITEMS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so tabs get registered
        CREATIVE_MODE_TABS.register(modEventBus);

        // Register the Deferred Register for conditional items
        OPTIONAL_ITEMS.register(modEventBus);
        OPTIONAL_BLOCKS.register(modEventBus);


        FLUIDS.register(modEventBus);
        FLUID_TYPES.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
    }


    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
        }
    }
}
