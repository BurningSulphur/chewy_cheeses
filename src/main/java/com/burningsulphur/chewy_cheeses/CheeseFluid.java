package com.burningsulphur.chewy_cheeses;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.fluids.FluidType;
import org.jetbrains.annotations.NotNull;
import umpaz.brewinandchewin.BrewinAndChewin;

import java.util.Objects;
import java.util.function.Consumer;

public class CheeseFluid  extends FluidType {

    public static final ResourceLocation RAT_LEICESTER_STILL_TEXTURE = new ResourceLocation(ChewyCheeses.MOD_ID, "block/rat_leicester_cheese_still");
    public static final ResourceLocation RAT_LEICESTER_FLOWING_TEXTURE = new ResourceLocation(ChewyCheeses.MOD_ID, "block/rat_leicester_cheese_flow");
    public static final ResourceLocation BLUE_STILL_TEXTURE = new ResourceLocation(ChewyCheeses.MOD_ID, "block/blue_cheese_still");
    public static final ResourceLocation BLUE_FLOWING_TEXTURE = new ResourceLocation(ChewyCheeses.MOD_ID, "block/blue_cheese_flow");
    public static final ResourceLocation NETHER_STILL_TEXTURE = new ResourceLocation(ChewyCheeses.MOD_ID, "block/nether_cheese_still");
    public static final ResourceLocation NETHER_FLOWING_TEXTURE = new ResourceLocation(ChewyCheeses.MOD_ID, "block/nether_cheese_flow");

    public static final ResourceLocation GLOW_STILL_TEXTURE = new ResourceLocation(ChewyCheeses.MOD_ID, "block/glow_cheese_still");
    public static final ResourceLocation GLOW_FLOWING_TEXTURE = new ResourceLocation(ChewyCheeses.MOD_ID, "block/glow_cheese_flow");

    public static final ResourceLocation WARDENZOLA_STILL_TEXTURE = new ResourceLocation(ChewyCheeses.MOD_ID, "block/wardenzola_cheese_still");
    public static final ResourceLocation WARDENZOLA_FLOWING_TEXTURE = new ResourceLocation(ChewyCheeses.MOD_ID, "block/wardenzola_cheese_flow");

    String type;
    public CheeseFluid(String type) {
        super(FluidType.Properties.create()
                .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
                .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
                .sound(SoundActions.FLUID_VAPORIZE, SoundEvents.FIRE_EXTINGUISH)
        );
        this.type = type;
    }

    @Override
    public void initializeClient(@NotNull Consumer<IClientFluidTypeExtensions> consumer) {
        consumer.accept(new IClientFluidTypeExtensions() {

            @Override
            public ResourceLocation getStillTexture()
            {
                if (Objects.equals("rat_leicester", type) ) {
                    return RAT_LEICESTER_STILL_TEXTURE;
                }
                if (Objects.equals("blue", type) ) {
                    return BLUE_STILL_TEXTURE;
                }
                if (Objects.equals("nether", type) ) {
                    return NETHER_STILL_TEXTURE;
                }

                if (Objects.equals("glow", type) ) {
                    return GLOW_STILL_TEXTURE;
                }
                if (Objects.equals("wardenzola", type) ) {
                    return WARDENZOLA_STILL_TEXTURE;
                }


                else {
                    return null;
                }
            }

            @Override
            public ResourceLocation getFlowingTexture()
            {
                if (Objects.equals("rat_leicester", type) ) {
                    return RAT_LEICESTER_FLOWING_TEXTURE;
                }
                if (Objects.equals("blue", type) ) {
                return BLUE_FLOWING_TEXTURE;
                }
                if (Objects.equals("nether", type) ) {
                    return NETHER_FLOWING_TEXTURE;
                }

                if (Objects.equals("glow", type) ) {
                    return GLOW_FLOWING_TEXTURE;
                }
                if (Objects.equals("wardenzola", type) ) {
                return WARDENZOLA_FLOWING_TEXTURE;
                }

                else {
                    return null;
                }
            }
        });
    }
}
