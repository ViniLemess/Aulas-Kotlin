package br.com.dionataferraz.vendas.viewmodel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.dionataferraz.vendas.R
import br.com.dionataferraz.vendas.databinding.ItemListBinding
import br.com.dionataferraz.vendas.toCurrency

class TransactionAdapter(private val listener: Listener) :
    RecyclerView.Adapter<TransactionViewHolder>() {

    interface Listener {
        fun onItemClick(text: String)
    }

    private val listItem: MutableList<Transaction> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TransactionViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val item = listItem[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return listItem.size
    }
    fun addNewList(list: List<Transaction>) {
        listItem.clear()
        notifyItemRangeRemoved(0, listItem.size)
        listItem.addAll(list)
    }
}

class TransactionViewHolder(
    private val binding: ItemListBinding,
    private val listener: TransactionAdapter.Listener
): RecyclerView.ViewHolder(binding.root) {

    fun bind(transaction: Transaction) {
        binding.transactionDescription.text = transaction.description
        "- ${transaction.amount.toCurrency()}".also { binding.transactionPrice.text = it }

        when (transaction.type) {
            TransactionType.GAS_STATION -> binding.transactionImg.setImageResource(R.drawable.gas_station)
            TransactionType.PUB -> binding.transactionImg.setImageResource(R.drawable.pub)
            TransactionType.MARKET -> binding.transactionImg.setImageResource(R.drawable.market)
        }

        binding.root.setOnClickListener {
            listener.onItemClick(transaction.amount.toString())
        }
    }
}
