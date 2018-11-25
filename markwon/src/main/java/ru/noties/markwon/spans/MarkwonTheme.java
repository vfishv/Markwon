package ru.noties.markwon.spans;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Px;
import android.support.annotation.Size;
import android.text.TextPaint;

import java.util.Arrays;
import java.util.Locale;

import ru.noties.markwon.utils.ColorUtils;
import ru.noties.markwon.utils.Dip;

@SuppressWarnings("WeakerAccess")
public class MarkwonTheme {

    /**
     * Factory method to obtain an instance of {@link MarkwonTheme} with all values as defaults
     *
     * @param context Context in order to resolve defaults
     * @return {@link MarkwonTheme} instance
     * @see #builderWithDefaults(Context)
     * @since 1.0.0
     */
    @NonNull
    public static MarkwonTheme create(@NonNull Context context) {
        return builderWithDefaults(context).build();
    }

    /**
     * Factory method to obtain an instance of {@link Builder}. Please note, that no default
     * values are set. This might be useful if you require a lot of special styling that differs
     * a lot with default one
     *
     * @return {@link Builder instance}
     * @see #builderWithDefaults(Context)
     * @see #builder(MarkwonTheme)
     * @since 1.0.0
     */
    @NonNull
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Factory method to create a {@link Builder} instance and initialize it with values
     * from supplied {@link MarkwonTheme}
     *
     * @param copyFrom {@link MarkwonTheme} to copy values from
     * @return {@link Builder} instance
     * @see #builderWithDefaults(Context)
     * @since 1.0.0
     */
    @NonNull
    public static Builder builder(@NonNull MarkwonTheme copyFrom) {
        return new Builder(copyFrom);
    }

    /**
     * Factory method to obtain a {@link Builder} instance initialized with default values taken
     * from current application theme.
     *
     * @param context Context to obtain default styling values (colors, etc)
     * @return {@link Builder} instance
     * @since 1.0.0
     */
    @NonNull
    public static Builder builderWithDefaults(@NonNull Context context) {

        final Dip dip = Dip.create(context);
        return new Builder()
                .codeMultilineMargin(dip.toPx(8))
                .blockMargin(dip.toPx(24))
                .blockQuoteWidth(dip.toPx(4))
                .bulletListItemStrokeWidth(dip.toPx(1))
                .headingBreakHeight(dip.toPx(1))
                .thematicBreakHeight(dip.toPx(4));
    }

    protected static final int BLOCK_QUOTE_DEF_COLOR_ALPHA = 25;

    protected static final int CODE_DEF_BACKGROUND_COLOR_ALPHA = 25;
    protected static final float CODE_DEF_TEXT_SIZE_RATIO = .87F;

    protected static final int HEADING_DEF_BREAK_COLOR_ALPHA = 75;

    // taken from html spec (most browsers render headings like that)
    // is not exposed via protected modifier in order to disallow modification
    private static final float[] HEADING_SIZES = {
            2.F, 1.5F, 1.17F, 1.F, .83F, .67F,
    };

    protected static final float SCRIPT_DEF_TEXT_SIZE_RATIO = .75F;

    protected static final int THEMATIC_BREAK_DEF_ALPHA = 25;

    protected final int linkColor;

    // used in quote, lists
    protected final int blockMargin;

    // by default it's 1/4th of `blockMargin`
    protected final int blockQuoteWidth;

    // by default it's text color with `BLOCK_QUOTE_DEF_COLOR_ALPHA` applied alpha
    protected final int blockQuoteColor;

    // by default uses text color (applied for un-ordered lists & ordered (bullets & numbers)
    protected final int listItemColor;

    // by default the stroke color of a paint object
    protected final int bulletListItemStrokeWidth;

    // width of bullet, by default min(blockMargin, height) / 2
    protected final int bulletWidth;

    // by default - main text color
    protected final int codeTextColor;

    // by default - codeTextColor
    protected final int codeBlockTextColor;

    // by default 0.1 alpha of textColor/codeTextColor
    protected final int codeBackgroundColor;

    // by default codeBackgroundColor
    protected final int codeBlockBackgroundColor;

    // by default `width` of a space char... it's fun and games, but span doesn't have access to paint in `getLeadingMargin`
    // so, we need to set this value explicitly (think of an utility method, that takes TextView/TextPaint and measures space char)
    protected final int codeMultilineMargin;

    // by default Typeface.MONOSPACE
    protected final Typeface codeTypeface;

    // by default a bit (how much?!) smaller than normal text
    // applied ONLY if default typeface was used, otherwise, not applied
    protected final int codeTextSize;

    // by default paint.getStrokeWidth
    protected final int headingBreakHeight;

    // by default, text color with `HEADING_DEF_BREAK_COLOR_ALPHA` applied alpha
    protected final int headingBreakColor;

    // by default, whatever typeface is set on the TextView
    // @since 1.1.0
    protected final Typeface headingTypeface;

    // by default, we use standard multipliers from the HTML spec (see HEADING_SIZES for values).
    // this library supports 6 heading sizes, so make sure the array you pass here has 6 elements.
    // @since 1.1.0
    protected final float[] headingTextSizeMultipliers;

    // by default `SCRIPT_DEF_TEXT_SIZE_RATIO`
    protected final float scriptTextSizeRatio;

    // by default textColor with `THEMATIC_BREAK_DEF_ALPHA` applied alpha
    protected final int thematicBreakColor;

    // by default paint.strokeWidth
    protected final int thematicBreakHeight;

    protected MarkwonTheme(@NonNull Builder builder) {
        this.linkColor = builder.linkColor;
        this.blockMargin = builder.blockMargin;
        this.blockQuoteWidth = builder.blockQuoteWidth;
        this.blockQuoteColor = builder.blockQuoteColor;
        this.listItemColor = builder.listItemColor;
        this.bulletListItemStrokeWidth = builder.bulletListItemStrokeWidth;
        this.bulletWidth = builder.bulletWidth;
        this.codeTextColor = builder.codeTextColor;
        this.codeBlockTextColor = builder.codeBlockTextColor;
        this.codeBackgroundColor = builder.codeBackgroundColor;
        this.codeBlockBackgroundColor = builder.codeBlockBackgroundColor;
        this.codeMultilineMargin = builder.codeMultilineMargin;
        this.codeTypeface = builder.codeTypeface;
        this.codeTextSize = builder.codeTextSize;
        this.headingBreakHeight = builder.headingBreakHeight;
        this.headingBreakColor = builder.headingBreakColor;
        this.headingTypeface = builder.headingTypeface;
        this.headingTextSizeMultipliers = builder.headingTextSizeMultipliers;
        this.scriptTextSizeRatio = builder.scriptTextSizeRatio;
        this.thematicBreakColor = builder.thematicBreakColor;
        this.thematicBreakHeight = builder.thematicBreakHeight;
    }

    /**
     * @since 1.0.5
     */
    public void applyLinkStyle(@NonNull TextPaint paint) {
        paint.setUnderlineText(true);
        if (linkColor != 0) {
            paint.setColor(linkColor);
        } else {
            // if linkColor is not specified during configuration -> use default one
            paint.setColor(paint.linkColor);
        }
    }

    public void applyLinkStyle(@NonNull Paint paint) {
        paint.setUnderlineText(true);
        if (linkColor != 0) {
            // by default we will be using text color
            paint.setColor(linkColor);
        } else {
            // @since 1.0.5, if link color is specified during configuration, _try_ to use the
            // default one (if provided paint is an instance of TextPaint)
            if (paint instanceof TextPaint) {
                paint.setColor(((TextPaint) paint).linkColor);
            }
        }
    }

    public void applyBlockQuoteStyle(@NonNull Paint paint) {
        final int color;
        if (blockQuoteColor == 0) {
            color = ColorUtils.applyAlpha(paint.getColor(), BLOCK_QUOTE_DEF_COLOR_ALPHA);
        } else {
            color = blockQuoteColor;
        }
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(color);
    }

    public int getBlockMargin() {
        return blockMargin;
    }

    public int getBlockQuoteWidth() {
        final int out;
        if (blockQuoteWidth == 0) {
            out = (int) (blockMargin * .25F + .5F);
        } else {
            out = blockQuoteWidth;
        }
        return out;
    }

    public void applyListItemStyle(@NonNull Paint paint) {

        final int color;
        if (listItemColor != 0) {
            color = listItemColor;
        } else {
            color = paint.getColor();
        }
        paint.setColor(color);

        if (bulletListItemStrokeWidth != 0) {
            paint.setStrokeWidth(bulletListItemStrokeWidth);
        }
    }

    public int getBulletWidth(int height) {

        final int min = Math.min(blockMargin, height) / 2;

        final int width;
        if (bulletWidth == 0
                || bulletWidth > min) {
            width = min;
        } else {
            width = bulletWidth;
        }

        return width;
    }

    /**
     * Modified in 1.0.5 to accept `multiline` argument
     */
    public void applyCodeTextStyle(@NonNull Paint paint, boolean multiline) {

        // @since 1.0.5 added handling of multiline code blocks
        if (multiline
                && codeBlockTextColor != 0) {
            paint.setColor(codeBlockTextColor);
        } else if (codeTextColor != 0) {
            paint.setColor(codeTextColor);
        }

        // custom typeface was set
        if (codeTypeface != null) {

            paint.setTypeface(codeTypeface);

            // please note that we won't be calculating textSize
            // (like we do when no Typeface is provided), if it's some specific typeface
            // we would confuse users about textSize
            if (codeTextSize != 0) {
                paint.setTextSize(codeTextSize);
            }

        } else {
            paint.setTypeface(Typeface.MONOSPACE);
            final float textSize;
            if (codeTextSize != 0) {
                textSize = codeTextSize;
            } else {
                textSize = paint.getTextSize() * CODE_DEF_TEXT_SIZE_RATIO;
            }
            paint.setTextSize(textSize);
        }
    }

    public int getCodeMultilineMargin() {
        return codeMultilineMargin;
    }

    /**
     * Modified in 1.0.5 to accept `multiline` argument
     */
    public int getCodeBackgroundColor(@NonNull Paint paint, boolean multiline) {

        final int color;

        // @since 1.0.5 added handling of multiline code blocks
        if (multiline
                && codeBlockBackgroundColor != 0) {
            color = codeBlockBackgroundColor;
        } else if (codeBackgroundColor != 0) {
            color = codeBackgroundColor;
        } else {
            color = ColorUtils.applyAlpha(paint.getColor(), CODE_DEF_BACKGROUND_COLOR_ALPHA);
        }

        return color;
    }

    public void applyHeadingTextStyle(@NonNull Paint paint, @IntRange(from = 1, to = 6) int level) {
        if (headingTypeface == null) {
            paint.setFakeBoldText(true);
        } else {
            paint.setTypeface(headingTypeface);
        }
        final float[] textSizes = headingTextSizeMultipliers != null
                ? headingTextSizeMultipliers
                : HEADING_SIZES;

        if (textSizes != null && textSizes.length >= level) {
            paint.setTextSize(paint.getTextSize() * textSizes[level - 1]);
        } else {
            throw new IllegalStateException(String.format(
                    Locale.US,
                    "Supplied heading level: %d is invalid, where configured heading sizes are: `%s`",
                    level, Arrays.toString(textSizes)));
        }
    }

    public void applyHeadingBreakStyle(@NonNull Paint paint) {
        final int color;
        if (headingBreakColor != 0) {
            color = headingBreakColor;
        } else {
            color = ColorUtils.applyAlpha(paint.getColor(), HEADING_DEF_BREAK_COLOR_ALPHA);
        }
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        if (headingBreakHeight >= 0) {
            //noinspection SuspiciousNameCombination
            paint.setStrokeWidth(headingBreakHeight);
        }
    }

    public void applySuperScriptStyle(@NonNull TextPaint paint) {
        final float ratio;
        if (Float.compare(scriptTextSizeRatio, .0F) == 0) {
            ratio = SCRIPT_DEF_TEXT_SIZE_RATIO;
        } else {
            ratio = scriptTextSizeRatio;
        }
        paint.setTextSize(paint.getTextSize() * ratio);
        paint.baselineShift += (int) (paint.ascent() / 2);
    }

    public void applySubScriptStyle(@NonNull TextPaint paint) {
        final float ratio;
        if (Float.compare(scriptTextSizeRatio, .0F) == 0) {
            ratio = SCRIPT_DEF_TEXT_SIZE_RATIO;
        } else {
            ratio = scriptTextSizeRatio;
        }
        paint.setTextSize(paint.getTextSize() * ratio);
        paint.baselineShift -= (int) (paint.ascent() / 2);
    }

    public void applyThematicBreakStyle(@NonNull Paint paint) {
        final int color;
        if (thematicBreakColor != 0) {
            color = thematicBreakColor;
        } else {
            color = ColorUtils.applyAlpha(paint.getColor(), THEMATIC_BREAK_DEF_ALPHA);
        }
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);

        if (thematicBreakHeight >= 0) {
            //noinspection SuspiciousNameCombination
            paint.setStrokeWidth(thematicBreakHeight);
        }
    }

    @SuppressWarnings("unused")
    public static class Builder {

        private int linkColor;
        private int blockMargin;
        private int blockQuoteWidth;
        private int blockQuoteColor;
        private int listItemColor;
        private int bulletListItemStrokeWidth;
        private int bulletWidth;
        private int codeTextColor;
        private int codeBlockTextColor; // @since 1.0.5
        private int codeBackgroundColor;
        private int codeBlockBackgroundColor; // @since 1.0.5
        private int codeMultilineMargin;
        private Typeface codeTypeface;
        private int codeTextSize;
        private int headingBreakHeight = -1;
        private int headingBreakColor;
        private Typeface headingTypeface;
        private float[] headingTextSizeMultipliers;
        private float scriptTextSizeRatio;
        private int thematicBreakColor;
        private int thematicBreakHeight = -1;

        Builder() {
        }

        Builder(@NonNull MarkwonTheme theme) {
            this.linkColor = theme.linkColor;
            this.blockMargin = theme.blockMargin;
            this.blockQuoteWidth = theme.blockQuoteWidth;
            this.blockQuoteColor = theme.blockQuoteColor;
            this.listItemColor = theme.listItemColor;
            this.bulletListItemStrokeWidth = theme.bulletListItemStrokeWidth;
            this.bulletWidth = theme.bulletWidth;
            this.codeTextColor = theme.codeTextColor;
            this.codeBlockTextColor = theme.codeBlockTextColor;
            this.codeBackgroundColor = theme.codeBackgroundColor;
            this.codeBlockBackgroundColor = theme.codeBlockBackgroundColor;
            this.codeMultilineMargin = theme.codeMultilineMargin;
            this.codeTypeface = theme.codeTypeface;
            this.codeTextSize = theme.codeTextSize;
            this.headingBreakHeight = theme.headingBreakHeight;
            this.headingBreakColor = theme.headingBreakColor;
            this.headingTypeface = theme.headingTypeface;
            this.headingTextSizeMultipliers = theme.headingTextSizeMultipliers;
            this.scriptTextSizeRatio = theme.scriptTextSizeRatio;
            this.thematicBreakColor = theme.thematicBreakColor;
            this.thematicBreakHeight = theme.thematicBreakHeight;
        }

        @NonNull
        public Builder linkColor(@ColorInt int linkColor) {
            this.linkColor = linkColor;
            return this;
        }

        @NonNull
        public Builder blockMargin(@Px int blockMargin) {
            this.blockMargin = blockMargin;
            return this;
        }

        @NonNull
        public Builder blockQuoteWidth(@Px int blockQuoteWidth) {
            this.blockQuoteWidth = blockQuoteWidth;
            return this;
        }

        @SuppressWarnings("SameParameterValue")
        @NonNull
        public Builder blockQuoteColor(@ColorInt int blockQuoteColor) {
            this.blockQuoteColor = blockQuoteColor;
            return this;
        }

        @NonNull
        public Builder listItemColor(@ColorInt int listItemColor) {
            this.listItemColor = listItemColor;
            return this;
        }

        @NonNull
        public Builder bulletListItemStrokeWidth(@Px int bulletListItemStrokeWidth) {
            this.bulletListItemStrokeWidth = bulletListItemStrokeWidth;
            return this;
        }

        @NonNull
        public Builder bulletWidth(@Px int bulletWidth) {
            this.bulletWidth = bulletWidth;
            return this;
        }

        @NonNull
        public Builder codeTextColor(@ColorInt int codeTextColor) {
            this.codeTextColor = codeTextColor;
            return this;
        }

        /**
         * @since 1.0.5
         */
        @NonNull
        public Builder codeBlockTextColor(@ColorInt int codeBlockTextColor) {
            this.codeBlockTextColor = codeBlockTextColor;
            return this;
        }

        @SuppressWarnings("SameParameterValue")
        @NonNull
        public Builder codeBackgroundColor(@ColorInt int codeBackgroundColor) {
            this.codeBackgroundColor = codeBackgroundColor;
            return this;
        }

        /**
         * @since 1.0.5
         */
        @NonNull
        public Builder codeBlockBackgroundColor(@ColorInt int codeBlockBackgroundColor) {
            this.codeBlockBackgroundColor = codeBlockBackgroundColor;
            return this;
        }

        @NonNull
        public Builder codeMultilineMargin(@Px int codeMultilineMargin) {
            this.codeMultilineMargin = codeMultilineMargin;
            return this;
        }

        @NonNull
        public Builder codeTypeface(@NonNull Typeface codeTypeface) {
            this.codeTypeface = codeTypeface;
            return this;
        }

        @NonNull
        public Builder codeTextSize(@Px int codeTextSize) {
            this.codeTextSize = codeTextSize;
            return this;
        }

        @NonNull
        public Builder headingBreakHeight(@Px int headingBreakHeight) {
            this.headingBreakHeight = headingBreakHeight;
            return this;
        }

        @NonNull
        public Builder headingBreakColor(@ColorInt int headingBreakColor) {
            this.headingBreakColor = headingBreakColor;
            return this;
        }

        /**
         * @param headingTypeface Typeface to use for heading elements
         * @return self
         * @since 1.1.0
         */
        @NonNull
        public Builder headingTypeface(@NonNull Typeface headingTypeface) {
            this.headingTypeface = headingTypeface;
            return this;
        }

        /**
         * @param headingTextSizeMultipliers an array of multipliers values for heading elements.
         *                                   The base value for this multipliers is TextView\'s text size
         * @return self
         * @since 1.1.0
         */
        @NonNull
        public Builder headingTextSizeMultipliers(@Size(6) @NonNull float[] headingTextSizeMultipliers) {
            this.headingTextSizeMultipliers = headingTextSizeMultipliers;
            return this;
        }

        @NonNull
        public Builder scriptTextSizeRatio(@FloatRange(from = .0F, to = Float.MAX_VALUE) float scriptTextSizeRatio) {
            this.scriptTextSizeRatio = scriptTextSizeRatio;
            return this;
        }

        @NonNull
        public Builder thematicBreakColor(@ColorInt int thematicBreakColor) {
            this.thematicBreakColor = thematicBreakColor;
            return this;
        }

        @NonNull
        public Builder thematicBreakHeight(@Px int thematicBreakHeight) {
            this.thematicBreakHeight = thematicBreakHeight;
            return this;
        }

        @NonNull
        public MarkwonTheme build() {
            return new MarkwonTheme(this);
        }
    }

}
