//
//  ContentView.swift
//  PogodexCollector
//
//  Created by Niels Marsman on 30/07/2022.
//

import SwiftUI
import PogodexLibrary

struct ContentView: View {
    
    private var viewModel = ViewModelProvider().pokemon

    @State var state: PokedexViewModel.State = PokedexViewModel.State(isLoading: true, pokemon: [])
    @State var text = "Waiting..."
    
    var body: some View {
        VStack {
            Text(text)
                .padding()

            Text(String(state.pokemon.count))
                .padding()

            Button("Reset", action: {
                Task {
                    viewModel.execute(action: PokedexViewModel.ActionPokemonClicked(pokemon: Pokemon(number: 1, name: "Bulbasaur"), collection: "Caught"))
                }
            })
        }
        .onAppear {
            Task {
                try await viewModel.state.watch { state in
                    guard let state = state else { return }
                    self.state = state
                }
            }
            Task {
                try await viewModel.event.watch { event in
                    self.text = "Event!"
                }
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
