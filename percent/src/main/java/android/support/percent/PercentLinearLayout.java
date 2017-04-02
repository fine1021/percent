package android.support.percent;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;


/**
 * Subclass of {@link LinearLayout} that supports percentage based dimensions and
 * margins.
 *
 * You can specify dimension or a margin of child by using attributes with "Percent" suffix. Follow
 * this example:
 *
 * <pre class="prettyprint">
 * &lt;android.support.percent.PercentLinearLayout
 *         xmlns:android="http://schemas.android.com/apk/res/android"
 *         xmlns:app="http://schemas.android.com/apk/res-auto"
 *         android:layout_width="match_parent"
 *         android:layout_height="match_parent"&gt;
 *     &lt;ImageView
 *         app:layout_widthPercent="50%"
 *         app:layout_heightPercent="50%"
 *         app:layout_marginTopPercent="25%"
 *         app:layout_marginLeftPercent="25%"/&gt;
 * &lt;/android.support.percent.PercentLinearLayout/&gt;
 * </pre>
 *
 * The attributes that you can use are:
 * <ul>
 *     <li>{@code layout_widthPercent}
 *     <li>{@code layout_heightPercent}
 *     <li>{@code layout_marginPercent}
 *     <li>{@code layout_marginLeftPercent}
 *     <li>{@code layout_marginTopPercent}
 *     <li>{@code layout_marginRightPercent}
 *     <li>{@code layout_marginBottomPercent}
 *     <li>{@code layout_marginStartPercent}
 *     <li>{@code layout_marginEndPercent}
 *     <li>{@code layout_aspectRatio}
 *     <li>{@code layout_percentBasedOn}
 *     <li>{@code layout_widthDimensionRatio}
 *     <li>{@code layout_heightDimensionRatio}
 *     <li>{@code layout_marginDimensionRatio}
 *     <li>{@code layout_marginLeftDimensionRatio}
 *     <li>{@code layout_marginTopDimensionRatio}
 *     <li>{@code layout_marginRightDimensionRatio}
 *     <li>{@code layout_marginBottomDimensionRatio}
 *     <li>{@code layout_marginStartDimensionRatio}
 *     <li>{@code layout_marginEndDimensionRatio}
 * </ul>
 *
 * It is not necessary to specify {@code layout_width/height} if you specify {@code
 * layout_widthPercent.} However, if you want the view to be able to take up more space than what
 * percentage value permits, you can add {@code layout_width/height="wrap_content"}. In that case
 * if the percentage size is too small for the View's content, it will be resized using
 * {@code wrap_content} rule.
 *
 * <p>
 * You can also make one dimension be a fraction of the other by setting only width or height and
 * using {@code layout_aspectRatio} for the second one to be calculated automatically. For
 * example, if you would like to achieve 16:9 aspect ratio, you can write:
 * <pre class="prettyprint">
 *     android:layout_width="300dp"
 *     app:layout_aspectRatio="178%"
 * </pre>
 * This will make the aspect ratio 16:9 (1.78:1) with the width fixed at 300dp and height adjusted
 * accordingly.
 */
public class PercentLinearLayout extends LinearLayout {

    private final PercentLayoutHelper mHelper = new PercentLayoutHelper(this);

    public PercentLinearLayout(Context context) {
        super(context);
    }

    public PercentLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PercentLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mHelper.adjustChildren(widthMeasureSpec, heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (mHelper.handleMeasuredStateTooSmall()) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        mHelper.restoreOriginalParams();
    }

    public static class LayoutParams extends LinearLayout.LayoutParams
            implements PercentLayoutHelper.PercentLayoutParams {

        private PercentLayoutHelper.PercentLayoutInfo mPercentLayoutInfo;

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            mPercentLayoutInfo = PercentLayoutHelper.getPercentLayoutInfo(c, attrs);
        }

        public LayoutParams(int width, int height) {
            super(width, height);
        }

        public LayoutParams(MarginLayoutParams source) {
            super(source);
        }

        public LayoutParams(ViewGroup.LayoutParams p) {
            super(p);
        }

        @Override
        public PercentLayoutHelper.PercentLayoutInfo getPercentLayoutInfo() {
            if (mPercentLayoutInfo == null) {
                mPercentLayoutInfo = new PercentLayoutHelper.PercentLayoutInfo();
            }
            return mPercentLayoutInfo;
        }

        @Override
        protected void setBaseAttributes(TypedArray a, int widthAttr, int heightAttr) {
            PercentLayoutHelper.fetchWidthAndHeight(this, a, widthAttr, heightAttr);
        }
    }
}
