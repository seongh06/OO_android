package ggum.oo.presentation.mypage

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ggum.oo.R
import ggum.oo.databinding.FragmentMypageBinding
import ggum.oo.presentation.base.BaseFragment
import ggum.oo.presentation.search.SearchHistoryRVA

@AndroidEntryPoint
class MypageFragment : BaseFragment<FragmentMypageBinding>(R.layout.fragment_mypage) {

    private lateinit var clubAdapter: MypageClubRVA
    private lateinit var myClubAdapter: MypageMyClubRVA

    private val clubList = listOf(
        "umc",
        "포롱",
        "아이콘",
        "CDZ",
        "구름톤",
        "스타티스트",
        "ASC",
        "GDG",
        "멋쟁이 사자처럼"
    )

    private val myclubList = listOf(
        "umc 7th",
        "포롱",
        "아이콘",
        "CDZ",
        "구름톤",
        "스타티스트",
        "ASC"
    )

    override fun initObserver() {

    }

    override fun initView() {

        myClubAdapter = MypageMyClubRVA(myclubList)
        clubAdapter = MypageClubRVA(clubList)

        binding.rvMypageMyClubList.adapter = myClubAdapter
        binding.rvMypageClubList.adapter = clubAdapter

        binding.rvMypageMyClubList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvMypageClubList.layoutManager = LinearLayoutManager(requireContext())

        binding.ivMypageToggleButton.setOnClickListener {
            if (binding.rvMypageClubList.visibility == View.VISIBLE) {
                binding.rvMypageClubList.visibility = View.GONE
            } else {
                binding.rvMypageClubList.visibility = View.VISIBLE
            }
        }
    }
}