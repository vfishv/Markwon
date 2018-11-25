package ru.noties.markwon;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.noties.markwon.image.AsyncDrawableLoader;
import ru.noties.markwon.renderer.ImageSize;
import ru.noties.markwon.renderer.ImageSizeResolver;
import ru.noties.markwon.spans.LinkSpan;
import ru.noties.markwon.spans.MarkwonTheme;

/**
 * Each method can return null or a Span object or an array of spans
 *
 * @since 1.1.0
 */
public interface SpannableFactory {

    @Nullable
    Object strongEmphasis();

    @Nullable
    Object emphasis();

    @Nullable
    Object blockQuote(@NonNull MarkwonTheme theme);

    @Nullable
    Object code(@NonNull MarkwonTheme theme, boolean multiline);

    @Nullable
    Object orderedListItem(@NonNull MarkwonTheme theme, int startNumber);

    @Nullable
    Object bulletListItem(@NonNull MarkwonTheme theme, int level);

    @Nullable
    Object thematicBreak(@NonNull MarkwonTheme theme);

    @Nullable
    Object heading(@NonNull MarkwonTheme theme, int level);

    @Nullable
    Object strikethrough();

    /**
     * @since 1.1.1
     */
    @Nullable
    Object paragraph(boolean inTightList);

    @Nullable
    Object image(
            @NonNull MarkwonTheme theme,
            @NonNull String destination,
            @NonNull AsyncDrawableLoader loader,
            @NonNull ImageSizeResolver imageSizeResolver,
            @Nullable ImageSize imageSize,
            boolean replacementTextIsLink);

    @Nullable
    Object link(
            @NonNull MarkwonTheme theme,
            @NonNull String destination,
            @NonNull LinkSpan.Resolver resolver);

    // Currently used by HTML parser
    @Nullable
    Object superScript(@NonNull MarkwonTheme theme);

    // Currently used by HTML parser
    @Nullable
    Object subScript(@NonNull MarkwonTheme theme);

    // Currently used by HTML parser
    @Nullable
    Object underline();
}
