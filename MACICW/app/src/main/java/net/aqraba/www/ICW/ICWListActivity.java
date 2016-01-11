package net.aqraba.www.ICW;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

import com.facebook.Profile;

import org.w3c.dom.Text;


/**
 * An activity representing a list of PayerTimes. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ICWDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p/>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link ICWListFragment} and the item details
 * (if present) is a {@link ICWDetailFragment}.
 * <p/>
 * This activity also implements the required
 * {@link ICWListFragment.Callbacks} interface
 * to listen for item selections.
 */
public class ICWListActivity extends FragmentActivity
        implements ICWListFragment.Callbacks {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icw_list);

        if (findViewById(R.id.icw_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((ICWListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.icw_list))
                    .setActivateOnItemClick(true);

        }

        Profile profile = Profile.getCurrentProfile();
        if (profile != null) {
            TextView textView = (TextView) findViewById(R.id.name);
            textView.setText(profile.getName());

        }

    }

    /**
     * Callback method from {@link ICWListFragment.Callbacks}
     * indicating that the item with the given ID was selected.
     */
    @Override
    public void onItemSelected(String id) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(ICWDetailFragment.ARG_ITEM_ID, id);
            ICWDetailFragment fragment = new ICWDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.icw_detail_container, fragment)
                    .commit();

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, ICWDetailActivity.class);
            detailIntent.putExtra(ICWDetailFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
        }
    }
}
