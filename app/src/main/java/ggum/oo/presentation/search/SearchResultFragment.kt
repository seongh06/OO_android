package ggum.oo.presentation.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import ggum.oo.R
import ggum.oo.databinding.FragmentSearchResultBinding
import ggum.oo.presentation.base.BaseFragment
import ggum.oo.presentation.search.list.AllListFragment
import ggum.oo.presentation.search.list.FavoriteListFragment
import ggum.oo.presentation.search.list.InSchoolListFragment
import ggum.oo.presentation.search.list.OutSchoolListFragment
import java.util.Locale.filter

@AndroidEntryPoint
class SearchResultFragment : BaseFragment<FragmentSearchResultBinding>(R.layout.fragment_search_result) {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var contentVPA: ContentVPA
    private val viewModel : SearchViewModel by activityViewModels()

    override fun initView() {
        tabLayout = binding.tabSearchResultCategory
        viewPager = binding.vpSearchResultList

        setupViewPager()
        setupSearchObserver() // 검색어 관찰 설정
    }

    private fun setupViewPager() {
        contentVPA = ContentVPA(this)
        viewPager.adapter = contentVPA

        // TabLayout과 ViewPager2 연결
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "통합"
                1 -> "교내"
                2 -> "교외"
                3 -> "관심"
                else -> null
            }
        }.attach()
    }

    override fun initObserver() {

    }

    private fun setupSearchObserver() {
        viewModel.searchResult.observe(viewLifecycleOwner) { query ->
            binding.etSearchBlock.setText(query) // EditText에 검색어 설정
            binding.etSearchBlock.setSelection(query.length) // 커서 위치 설정
        }
    }
}
