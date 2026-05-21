import React, { useState, useEffect } from 'react';
import { Calendar, Scissors, User, CheckCircle2, ChevronRight, Loader2 } from 'lucide-react';

// Tipagens para o TypeScript não reclamar
interface Servico {
  id: number;
  nome: string;
  preco: number;
  tempoEstimadoMinutos: number;
}

interface Barbeiro {
  id: number;
  nome: string;
  especialidade: string;
}

// URL dinâmica para nuvem ou localhost
const API_BASE_URL = import.meta.env.VITE_API_URL || 'http://localhost:8080';

export default function AgendamentoPage() {
  const [servicos, setServicos] = useState<Servico[]>([]);
  const [barbeiros, setBarbeiros] = useState<Barbeiro[]>([]);
  
  const [servicoSelecionado, setServicoSelecionado] = useState<number | null>(null);
  const [barbeiroSelecionado, setBarbeiroSelecionado] = useState<number | null>(null);
  const [dataHora, setDataHora] = useState<string>('');
  
  const [carregando, setCarregando] = useState(false);

  // Busca os dados reais usando a URL dinâmica
  useEffect(() => {
    fetch(`${API_BASE_URL}/servicos`)
      .then(res => res.json())
      .then(data => setServicos(data))
      .catch(err => console.error("Erro ao buscar serviços:", err));

    fetch(`${API_BASE_URL}/barbeiros`)
      .then(res => res.json())
      .then(data => setBarbeiros(data))
      .catch(err => console.error("Erro ao buscar barbeiros:", err));
  }, []);

  const handleSubmit = async (e: React.SyntheticEvent) => {
    e.preventDefault();
    
    if (!servicoSelecionado || !barbeiroSelecionado || !dataHora) {
      alert("Por favor, preencha todos os passos antes de confirmar!");
      return;
    }

    setCarregando(true);

    try {
      const response = await fetch(`${API_BASE_URL}/agendamentos`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          clienteId: 1, // Hardcoded: O ID do Cliente
          barbeiroId: barbeiroSelecionado,
          dataHora: dataHora + ":00" 
        }),
      });

      if (response.status === 201) {
        alert('🎉 Agendamento Confirmado com Sucesso!');
        setServicoSelecionado(null);
        setBarbeiroSelecionado(null);
        setDataHora('');
      } else {
        alert('⚠️ Ops! Deu um erro ao agendar. Verifique o console.');
      }
    } catch (error) {
      console.error("Erro de conexão:", error);
      alert('Erro de conexão com o servidor. O back-end está rodando?');
    } finally {
      setCarregando(false);
    }
  };

  return (
    <div className="min-h-screen bg-zinc-950 text-zinc-100 flex justify-center items-center p-4 md:p-8 font-sans selection:bg-amber-500 selection:text-zinc-900">
      <div className="max-w-5xl w-full bg-zinc-900 rounded-3xl shadow-2xl shadow-black/50 overflow-hidden flex flex-col md:flex-row border border-zinc-800/50 relative z-10">
        
        {/* Painel Esquerdo */}
        <div className="md:w-2/5 relative p-10 flex flex-col justify-between overflow-hidden min-h-[300px]">
          <div className="absolute inset-0 bg-[url('https://images.unsplash.com/photo-1585747860715-2ba37e788b70?q=80&w=1000&auto=format&fit=crop')] bg-cover bg-center"></div>
          {/* Atualizado para bg-linear-to-t */}
          <div className="absolute inset-0 bg-linear-to-t from-zinc-950 via-zinc-950/80 to-zinc-900/60 backdrop-blur-[2px]"></div>
          
          <div className="relative z-10">
            {/* Atualizado para bg-linear-to-r */}
            <h1 className="text-4xl font-extrabold tracking-tighter text-transparent bg-clip-text bg-linear-to-r from-amber-400 to-amber-600 mb-2">
              Barber<span className="text-white">Connect</span>
            </h1>
            <p className="text-zinc-300 text-sm font-medium">A sua barbearia clássica,<br/>a um clique de distância.</p>
          </div>
          
          <div className="relative z-10 hidden md:block mt-12">
            {/* Atualizado para bg-linear-to-r */}
            <div className="w-12 h-1 bg-linear-to-r from-amber-500 to-transparent mb-6 rounded-full"></div>
            <p className="text-zinc-400 italic text-sm leading-relaxed border-l-2 border-zinc-700 pl-4">
              "O estilo é a perfeição de um ponto de vista. Reserve seu momento."
            </p>
          </div>
        </div>

        {/* Formulário Principal */}
        <div className="md:w-3/5 p-8 md:p-12 bg-zinc-900 relative">
          <div className="absolute top-0 right-0 w-64 h-64 bg-amber-500/5 rounded-full blur-3xl -z-10 pointer-events-none"></div>

          <h2 className="text-3xl font-bold mb-8 text-white flex items-center gap-3">
            <Calendar className="text-amber-500" size={28} />
            Novo Agendamento
          </h2>

          <form onSubmit={handleSubmit} className="space-y-8 relative z-10">
            
            {/* Seção de Serviços */}
            <div>
              <p className="flex items-center gap-2 text-xs font-bold text-zinc-400 mb-4 uppercase tracking-widest">
                <Scissors size={14} className="text-amber-500" />
                1. Escolha o Serviço
              </p>
              {servicos.length === 0 ? <p className="text-zinc-500 text-sm animate-pulse">Carregando serviços do banco...</p> : (
                <div className="grid grid-cols-1 sm:grid-cols-2 gap-4">
                  {servicos.map(srv => {
                    const isSelected = servicoSelecionado === srv.id;
                    return (
                      <button key={srv.id} type="button" onClick={() => setServicoSelecionado(srv.id)}
                        className={`relative p-5 rounded-2xl text-left transition-all duration-300 hover:-translate-y-1 ${isSelected ? 'bg-amber-500 text-zinc-950 shadow-lg shadow-amber-500/20' : 'bg-zinc-800/50 text-white border border-zinc-700/50 hover:border-amber-500/50 hover:bg-zinc-800'}`}
                      >
                        {isSelected && <CheckCircle2 size={18} className="absolute top-4 right-4 text-zinc-950" />}
                        <p className="font-bold text-lg">{srv.nome}</p>
                        <p className={`text-sm mt-1 font-medium ${isSelected ? 'text-zinc-800' : 'text-amber-500'}`}>
                          R$ {srv.preco.toFixed(2)} &bull; {srv.tempoEstimadoMinutos} min
                        </p>
                      </button>
                    );
                  })}
                </div>
              )}
            </div>

            {/* Seção de Barbeiros */}
            <div>
              <p className="flex items-center gap-2 text-xs font-bold text-zinc-400 mb-4 uppercase tracking-widest">
                <User size={14} className="text-amber-500" />
                2. Escolha o Profissional
              </p>
              {barbeiros.length === 0 ? <p className="text-zinc-500 text-sm animate-pulse">Carregando barbeiros do banco...</p> : (
                <div className="grid grid-cols-1 sm:grid-cols-2 gap-4">
                  {barbeiros.map(barb => {
                    const isSelected = barbeiroSelecionado === barb.id;
                    return (
                      <button key={barb.id} type="button" onClick={() => setBarbeiroSelecionado(barb.id)}
                        className={`relative p-4 rounded-2xl flex items-center gap-4 transition-all duration-300 hover:-translate-y-1 ${isSelected ? 'bg-zinc-800 border-2 border-amber-500' : 'bg-zinc-800/50 border-2 border-transparent border-zinc-700/50 hover:border-zinc-500'}`}
                      >
                        <span className={`text-2xl rounded-full w-12 h-12 flex items-center justify-center transition-colors ${isSelected ? 'bg-amber-500/20' : 'bg-zinc-700/50'}`}>
                          🧔🏻
                        </span>
                        <div className="text-left">
                          <p className="font-bold text-white">{barb.nome}</p>
                          <p className="text-zinc-400 text-xs font-medium">{barb.especialidade}</p>
                        </div>
                        {isSelected && <CheckCircle2 size={18} className="absolute top-4 right-4 text-amber-500" />}
                      </button>
                    );
                  })}
                </div>
              )}
            </div>

            {/* Seção de Data e Hora */}
            <div>
              <label htmlFor="dataAgendamento" className="flex items-center gap-2 text-xs font-bold text-zinc-400 mb-4 uppercase tracking-widest">
                <Calendar size={14} className="text-amber-500" />
                3. Data e Horário
              </label>
              <input 
                id="dataAgendamento" 
                type="datetime-local" 
                value={dataHora}
                onChange={(e) => setDataHora(e.target.value)}
                className="w-full bg-zinc-800/80 border border-zinc-700 rounded-xl p-4 text-white font-medium focus:outline-none focus:border-amber-500 focus:ring-2 focus:ring-amber-500/20 transition-all"
                required
              />
            </div>

            {/* Atualizado para bg-linear-to-r */}
            <button 
              type="submit"
              disabled={carregando}
              className="group w-full bg-linear-to-r from-amber-600 to-amber-500 hover:from-amber-500 hover:to-amber-400 text-zinc-950 font-bold text-lg py-4 rounded-xl transition-all duration-300 shadow-xl shadow-amber-500/20 flex justify-center items-center gap-2 transform hover:scale-[1.02] active:scale-[0.98] disabled:opacity-70 disabled:scale-100"
            >
              {carregando ? (
                <>Processando... <Loader2 size={20} className="animate-spin" /></>
              ) : (
                <>Confirmar Reserva <ChevronRight size={20} className="group-hover:translate-x-1 transition-transform" /></>
              )}
            </button>
          </form>
        </div>

      </div>
    </div>
  );
}