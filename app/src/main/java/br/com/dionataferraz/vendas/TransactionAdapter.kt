package br.com.dionataferraz.vendas

import android.text.format.DateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.dionataferraz.vendas.databinding.ItemListBinding
import java.text.SimpleDateFormat
import java.util.*

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

    fun addList(list: List<Transaction>) {
        listItem.addAll(list)
        notifyItemRangeInserted(0, listItem.size)
    }
    fun updateItem(transaction: Transaction, position: Int) {
        listItem[position] = transaction
        notifyItemChanged(position)
    }

}

class TransactionViewHolder(
    private val binding: ItemListBinding,
    private val listener: TransactionAdapter.Listener
): RecyclerView.ViewHolder(binding.root) {

    fun bind(transaction: Transaction) {
        binding.transactionName.text = transaction.description
        binding.transactionPrice.text = "R$${transaction.price}"
        Log.i("====> DATE",transaction.date)
        binding.transactionDate.text = transaction.date

        when (transaction.type) {
            TransactionType.MARKET -> binding.transactionImg.setImageResource(R.drawable.icons_market)
            TransactionType.GAS_STATION -> binding.transactionImg.setImageResource(R.drawable.icons_gas)
            TransactionType.PUB -> binding.transactionImg.setImageResource(R.drawable.icons_pub)
        }

        binding.root.setOnClickListener {
            listener.onItemClick(transaction.description)
        }
    }
}
