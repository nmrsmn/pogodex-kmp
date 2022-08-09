//
//  ContentView.swift
//  PogodexCollector
//
//  Created by Niels Marsman on 30/07/2022.
//

import SwiftUI
import PogodexLibrary

struct ContentView: View {
    @State var text = "Waiting..."
    @State var counter = 0
    
    var body: some View {
        VStack {
            Text(text)
                .padding()
                .onAppear {
                    Task {
                        do {
                            text = try await GreetingHelper().greet()
                        } catch {
                            text = error.localizedDescription
                        }
                    }
                }
            Text("\(counter)")
                .padding()
        }
        .onAppear {
            Task {
                do {
                    // Option 1; Can also provide fail option
                    try await CounterServiceHelper().service.counter.collect(collector: Observer<KotlinInt> { value in
                        self.counter = value.intValue
                    })
                    
                    // Option 2
                    try await CounterServiceHelper().service.counter.watch { value in
                        self.counter = value?.intValue ?? 0
                    }
                } catch {
                    text = error.localizedDescription
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

typealias Collector = UtilCommonFlowCollector
class Observer<T> : Collector {
    let callback: (T) -> Void
    let fail: (Error) -> Void
    
    init(callback: @escaping (T) -> Void, fail: @escaping (Error) -> Void = { _ in }) {
        self.callback = callback
        self.fail = fail
    }
    
    func emit(value: Any?) async throws {
        if let value = value as? T {
            callback(value)
        }
    }
    
    func fail(exception: KotlinException) async throws {
        self.fail(exception.asError())
    }
}
