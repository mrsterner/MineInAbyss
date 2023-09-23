package dev.sterner.mineinabyss;

import dev.sterner.mineinabyss.data.*;
import dev.sterner.mineinabyss.registry.MIAItems;
import dev.sterner.mineinabyss.registry.MIARegistries;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.concurrent.CompletableFuture;

import static dev.sterner.mineinabyss.registry.MIABlockEntityTypes.BLOCK_ENTITY_TYPES;
import static dev.sterner.mineinabyss.registry.MIABlocks.BLOCKS;
import static dev.sterner.mineinabyss.registry.MIAEntityTypes.ENTITY_TYPES;
import static dev.sterner.mineinabyss.registry.MIAItems.ITEMS;
import static dev.sterner.mineinabyss.registry.MIAMobEffects.MOB_EFFECTS;
import static dev.sterner.mineinabyss.registry.MIAParticleTypes.PARTICLE_TYPES;


@Mod(MineInAbyss.MODID)
public class MineInAbyss {
    public static final String MODID = "mineinabyss";

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);


    public static final RegistryObject<CreativeModeTab> MIA_TAB = CREATIVE_MODE_TABS.register(MODID,
            () -> CreativeModeTab.builder()
                    .withTabsBefore(CreativeModeTabs.COMBAT)
                    .title(Component.translatable("itemGroup." + MODID + ".main"))
                    .icon(() -> MIAItems.STAR_COMPASS.get().getDefaultInstance())
                    .displayItems((parameters, output) -> {
                        output.accept(MIAItems.STAR_COMPASS.get());
                    }).build()
    );

    public MineInAbyss() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        BLOCKS.register(modEventBus);
        BLOCK_ENTITY_TYPES.register(modEventBus);
        ITEMS.register(modEventBus);
        ENTITY_TYPES.register(modEventBus);
        MOB_EFFECTS.register(modEventBus);
        PARTICLE_TYPES.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);

        MIARegistries.CURSE_DEFERRED_REGISTER.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);
        modEventBus.addListener(this::gatherData);
    }

    public static ResourceLocation id(String id) {
        return new ResourceLocation(MODID, id);
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == MIA_TAB.getKey()) {
            event.accept(MIAItems.STAR_COMPASS);
            event.accept(MIAItems.THOUSAND_MEN_PINS);
            event.accept(MIAItems.CRADLE_OF_DESIRE);
            event.accept(MIAItems.MARBLE);
            event.accept(MIAItems.FLESH);
            event.accept(MIAItems.CURSE_WARDING_BOX);
        }
    }


    public void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> provider = event.getLookupProvider();
        ExistingFileHelper helper = event.getExistingFileHelper();

        MIAItemModelProvider itemProvider = new MIAItemModelProvider(output, helper);
        MIABlockStateProvider blockStateProvider = new MIABlockStateProvider(output, helper, itemProvider);
        MIALanguageProvider langProvider = new MIALanguageProvider(output);
        MIATagProvider.MIAEntityTypes entityTagProvider = new MIATagProvider.MIAEntityTypes(output, provider, helper);
        MIATagProvider.MIABlocks blockTags = new MIATagProvider.MIABlocks(output, provider, helper);
        MIATagProvider.MIAItems itemTags = new MIATagProvider.MIAItems(output, provider, blockTags.contentsGetter(), helper);
        MIALootTableProvider lootTables = new MIALootTableProvider(output);

        generator.addProvider(event.includeServer(), blockTags);
        generator.addProvider(event.includeServer(), itemTags);
        generator.addProvider(event.includeServer(), entityTagProvider);
        generator.addProvider(event.includeServer(), lootTables);

        generator.addProvider(event.includeClient(), blockStateProvider);
        generator.addProvider(event.includeClient(), itemProvider);
        generator.addProvider(event.includeClient(), langProvider);

    }
}
